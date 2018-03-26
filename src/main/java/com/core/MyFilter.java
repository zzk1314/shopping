package com.core;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MyFilter implements Filter{
	String charset = null;
	public void init(FilterConfig filterConfig) throws ServletException {
		charset = filterConfig.getInitParameter("charset");
	}

	
	//过滤器需要配置 且配置的代码要写在servlet的前面
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String method = request.getMethod();
		if("get".equalsIgnoreCase(method)){
			request = new MyRequest(request);
			chain.doFilter(request, response);
		}else if("post".equalsIgnoreCase(method)){
			request.setCharacterEncoding(charset);
			chain.doFilter(request, response);
		}
	}
	
	public class MyRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		
		@Override
		public String getParameter(String name) {
			String value = request.getParameter(name);
			if(value!=null){
				try {
					value = new String(value.getBytes("iso-8859-1"),MyFilter.this.charset);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return value;
		}
		@Override
		public String[] getParameterValues(String name) {
			String[] values = request.getParameterValues(name);
			if(values.length!=0){
				for(int i =0 ;i<values.length;i++){
					try {
						values[i] = new String(values[i].getBytes("iso-8859-1"),MyFilter.this.charset);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
			return values;
		}
	}

	public void destroy() {
		
	}


}
