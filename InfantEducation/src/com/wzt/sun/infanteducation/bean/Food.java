package com.wzt.sun.infanteducation.bean;

public class Food {
	private int fo_authr;
	private String fo_date;
	private int fo_id;
	private String fo_img;
	private String fo_name;
	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFo_authr() {
		return fo_authr;
	}
	public void setFo_authr(int fo_authr) {
		this.fo_authr = fo_authr;
	}
	public String getFo_date() {
		return fo_date;
	}
	public void setFo_date(String fo_date) {
		this.fo_date = fo_date;
	}
	public int getFo_id() {
		return fo_id;
	}
	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}
	public String getFo_img() {
		return fo_img;
	}
	public void setFo_img(String fo_img) {
		this.fo_img = fo_img;
	}
	public String getFo_name() {
		return fo_name;
	}
	public void setFo_name(String fo_name) {
		this.fo_name = fo_name;
	}
	@Override
	public String toString() {
		return "Food [fo_authr=" + fo_authr + ", fo_date=" + fo_date + ", fo_id=" + fo_id + ", fo_img=" + fo_img
				+ ", fo_name=" + fo_name + "]";
	}
	
}
