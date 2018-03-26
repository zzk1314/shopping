package com.core;

import java.util.HashMap;
import java.util.Map;

public class ActionConfig {
	private String name ; 
	private String className ; 
	private String method ;
	private Map<String,ResultConfig> resMap = new HashMap<String,ResultConfig>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public Map<String, ResultConfig> getResMap() {
		return resMap;
	}
	public void setResMap(Map<String, ResultConfig> resMap) {
		this.resMap = resMap;
	}
	@Override
	public String toString() {
		return "ActionConfig [className=" + className + ", method=" + method
				+ ", name=" + name + ", resMap=" + resMap + "]";
	}
	
	
	
	
	
	
	
	
}
