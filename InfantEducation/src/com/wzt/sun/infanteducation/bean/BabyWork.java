package com.wzt.sun.infanteducation.bean;

import java.io.Serializable;

public class BabyWork implements Serializable{
	private static final long serialVersionUID = 111111111;
	private String W_title;//标题
	private int W_id;//id
	private String W_date;// 时间
	private String W_img;//图片
	private String W_teacher;//老师
	private String W_remark;//点评
	public BabyWork() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getW_title() {
		return W_title;
	}
	public void setW_title(String w_title) {
		W_title = w_title;
	}
	public int getW_id() {
		return W_id;
	}
	public void setW_id(int w_id) {
		W_id = w_id;
	}
	public String getW_date() {
		return W_date;
	}
	public void setW_date(String w_date) {
		W_date = w_date;
	}
	public String getW_img() {
		return W_img;
	}
	public void setW_img(String w_img) {
		W_img = w_img;
	}
	public String getW_teacher() {
		return W_teacher;
	}
	public void setW_teacher(String w_teacher) {
		W_teacher = w_teacher;
	}
	public String getW_remark() {
		return W_remark;
	}
	public void setW_remark(String w_remark) {
		W_remark = w_remark;
	}
	@Override
	public String toString() {
		return "BabyWork [W_title=" + W_title + ", W_id=" + W_id + ", W_date=" + W_date + ", W_img=" + W_img
				+ ", W_teacher=" + W_teacher + ", W_remark=" + W_remark + "]";
	}
	
}
