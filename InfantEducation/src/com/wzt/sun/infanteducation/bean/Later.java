package com.wzt.sun.infanteducation.bean;

public class Later {
	private String f_content;
	private String f_authr;
	private int s_id;
	private int f_id;
	private String f_date;
	public Later() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getF_content() {
		return f_content;
	}
	public void setF_content(String f_content) {
		this.f_content = f_content;
	}
	public String getF_authr() {
		return f_authr;
	}
	public void setF_authr(String f_authr) {
		this.f_authr = f_authr;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getF_date() {
		return f_date;
	}
	public void setF_date(String f_date) {
		this.f_date = f_date;
	}
	@Override
	public String toString() {
		return "Later [f_content=" + f_content + ", f_authr=" + f_authr + ", s_id=" + s_id + ", f_id=" + f_id
				+ ", f_date=" + f_date + "]";
	}
	
}
