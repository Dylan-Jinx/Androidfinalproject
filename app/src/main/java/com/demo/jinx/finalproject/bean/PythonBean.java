package com.demo.jinx.finalproject.bean;

import java.io.Serializable;

public class PythonBean implements Serializable {
	private String address;
	private int id;
	private String openClass;
	private String content;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOpenClass(String openClass){
		this.openClass = openClass;
	}

	public String getOpenClass(){
		return openClass;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"PythonBean{" + 
			"address = '" + address + '\'' + 
			",id = '" + id + '\'' + 
			",open_class = '" + openClass + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}