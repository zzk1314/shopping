package com.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionContext {
	private HttpServletRequest request ; 
	private HttpServletResponse  response ;
	//通过ThreadLocal解决线程安全性问题
	private static ThreadLocal<ActionContext> thread = new ThreadLocal<ActionContext>();
	public static void setActionContext(ActionContext ac) {
		thread.set(ac) ;
	}
	
	public static ActionContext getActionContext(){
		return thread.get() ; 
	}
	
	public ActionContext(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	} 
	
}
