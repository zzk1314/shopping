package com.core;

public class ResultConfig {
	// 三个个属性
	private String name ; //逻辑视图名
	private String type  ;//跳转类型
	private String path ;// 物理视图
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "ResultConfig [name=" + name + ", path=" + path + ", type="
				+ type + "]";
	} 
	
	
	
}
