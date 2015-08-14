package com.wzt.sun.infanteducation.bean;

public class Education {
	private String sy_content;
	private String sy_date;
	private int sy_id;
	private String sy_img;
	private String sy_title;
	private int sy_type;
	public Education() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSy_content() {
		return sy_content;
	}
	public void setSy_content(String sy_content) {
		this.sy_content = sy_content;
	}
	public String getSy_date() {
		return sy_date;
	}
	public void setSy_date(String sy_date) {
		this.sy_date = sy_date;
	}
	public int getSy_id() {
		return sy_id;
	}
	public void setSy_id(int sy_id) {
		this.sy_id = sy_id;
	}
	public String getSy_img() {
		return sy_img;
	}
	public void setSy_img(String sy_img) {
		this.sy_img = sy_img;
	}
	public String getSy_title() {
		return sy_title;
	}
	public void setSy_title(String sy_title) {
		this.sy_title = sy_title;
	}
	public int getSy_type() {
		return sy_type;
	}
	public void setSy_type(int sy_type) {
		this.sy_type = sy_type;
	}
	@Override
	public String toString() {
		return "Education [sy_content=" + sy_content + ", sy_date=" + sy_date + ", sy_id=" + sy_id + ", sy_img="
				+ sy_img + ", sy_title=" + sy_title + ", sy_type=" + sy_type + "]";
	}
	
}
