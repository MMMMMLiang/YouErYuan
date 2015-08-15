package com.wzt.sun.infanteducation.bean;

import java.io.Serializable;

public class Homework implements Serializable{
	private int h_id; 
	private String h_title;
	private int h_authr;
	private String h_content;
	private String h_date;
	private int c_id;
	public Homework() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getH_id() {
		return h_id;
	}
	public void setH_id(int h_id) {
		this.h_id = h_id;
	}
	public String getH_title() {
		return h_title;
	}
	public void setH_title(String h_title) {
		this.h_title = h_title;
	}
	public int getH_authr() {
		return h_authr;
	}
	public void setH_authr(int h_authr) {
		this.h_authr = h_authr;
	}
	public String getH_content() {
		return h_content;
	}
	public void setH_content(String h_content) {
		this.h_content = h_content;
	}
	public String getH_date() {
		return h_date;
	}
	public void setH_date(String h_date) {
		this.h_date = h_date;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	@Override
	public String toString() {
		return "Homework [h_id=" + h_id + ", h_title=" + h_title + ", h_authr=" + h_authr + ", h_content=" + h_content
				+ ", h_date=" + h_date + ", c_id=" + c_id + "]";
	}
	
	
}
