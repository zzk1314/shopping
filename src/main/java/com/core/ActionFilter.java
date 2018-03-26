package com.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionFilter implements Filter {
	public Map<String,ActionConfig> actionMap = new HashMap<String,ActionConfig>();
	public void init(FilterConfig filterConfig) throws ServletException {
		SAXReader reader = new SAXReader ();
		try {
			Document doc = reader.read(ActionFilter.class.getClassLoader().getResourceAsStream("actions.xml"));
			Element root = doc.getRootElement() ; 
			List actionList = root.elements("action") ; 
			//读取action子节点
			for(int i=0;i<actionList.size();i++) {
				Element e = (Element) actionList.get(i);
				String name = e.attributeValue("name") ; 
				String className = e.attributeValue("class") ;
				String method = e.attributeValue("method") ;
				ActionConfig ac = new ActionConfig() ; 
				ac.setName(name) ;
				ac.setClassName(className) ;
				ac.setMethod(method);
				actionMap.put(name, ac) ;
				//读取action下的result节点
				List resultList = e.elements("result") ;
				for(int j=0;j<resultList.size();j++) {
					Element result = (Element) resultList.get(j);
					String resultName =result.attributeValue("name") ;
					String resultType = result.attributeValue("type") ;
					String path= result.getText();
					//创建ResultConfig对象
					ResultConfig rc  = new ResultConfig() ;
					rc.setName(resultName) ;
					rc.setType(resultType) ;
					rc.setPath(path) ;
					ac.getResMap().put(resultName, rc) ;
				}
 			}
			//System.out.println(actionMap);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("没有找到actions.xml文件");
		}
	}
	
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req ;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getRequestURI();
		String suffied  = uri.substring(request.getContextPath().length()+1);
		if(actionMap.containsKey(suffied)){
			//根据key去找ActionConfig这个对象
			ActionConfig ac = actionMap.get(suffied) ;
			String className = ac.getClassName() ;
			String methodName= ac.getMethod();
			try {
				//创建对象的操作
				Object obj = Class.forName(className).newInstance();
				ActionContext actionContext = new ActionContext(request,response) ;
				ActionContext.setActionContext(actionContext) ;
				Method method =Class.forName(className).getDeclaredMethod(methodName, null);
				String logicView = (String) method.invoke(obj, null) ;
				ResultConfig rc = ac.getResMap().get(logicView) ;
				if(rc==null) {
					throw new RuntimeException("该"+ac.getName()+"不存在对应的视图");
				}else{
					//跳转类型
					String type = rc.getType() ;
					String path = rc.getPath() ; 
					if("dispatcher".equalsIgnoreCase(type)) {
						request.getRequestDispatcher(path).forward(request, response) ;
					}else if("redirect".equalsIgnoreCase(type)){
						response.sendRedirect(path) ;
					}else if("stream".equalsIgnoreCase(type)) {
						//
					}
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}else{
			chain.doFilter(request, response);
		}

	}
	public void destroy() {
		
	}


}
