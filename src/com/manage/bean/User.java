package com.manage.bean;

public class User {
	private String name;
	private String passwd;
	private String test;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPasswd() {
		return passwd.hashCode();
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
