package com.wzt.sun.infanteducation.bean;

public class User {
	private String user;
	private int num;
	private String state;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [user=" + user + ", num=" + num + ", state=" + state + "]";
	}
	
}
