package com.wzt.sun.infanteducation.bean;

import java.io.Serializable;

/**
 * 课程表
 * @author sun
 *
 */
public class Syllabus implements Serializable{
	private static final long serialVersionUID = 111111113;
	private int c_id;
	private String c_name;
	private String sy_am;
	private String sy_authr;
	private String sy_date;
	private String sy_id;
	private String sy_pm;
	private String sy_uploaddate;
	private String sy_week;
	
	public Syllabus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getSy_am() {
		return sy_am;
	}
	public void setSy_am(String sy_am) {
		this.sy_am = sy_am;
	}
	public String getSy_authr() {
		return sy_authr;
	}
	public void setSy_authr(String sy_authr) {
		this.sy_authr = sy_authr;
	}
	public String getSy_date() {
		return sy_date;
	}
	public void setSy_date(String sy_date) {
		this.sy_date = sy_date;
	}
	public String getSy_id() {
		return sy_id;
	}
	public void setSy_id(String sy_id) {
		this.sy_id = sy_id;
	}
	public String getSy_pm() {
		return sy_pm;
	}
	public void setSy_pm(String sy_pm) {
		this.sy_pm = sy_pm;
	}
	public String getSy_uploaddate() {
		return sy_uploaddate;
	}
	public void setSy_uploaddate(String sy_uploaddate) {
		this.sy_uploaddate = sy_uploaddate;
	}
	public String getSy_week() {
		return sy_week;
	}
	public void setSy_week(String sy_week) {
		this.sy_week = sy_week;
	}
	@Override
	public String toString() {
		return "Syllabus [c_id=" + c_id + ", c_name=" + c_name + ", sy_am=" + sy_am + ", sy_authr=" + sy_authr
				+ ", sy_date=" + sy_date + ", sy_id=" + sy_id + ", sy_pm=" + sy_pm + ", sy_uploaddate=" + sy_uploaddate
				+ ", sy_week=" + sy_week + "]";
	}
	
	

}
