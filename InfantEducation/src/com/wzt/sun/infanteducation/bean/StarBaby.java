package com.wzt.sun.infanteducation.bean;

public class StarBaby {
	private String sb_img;
	private String sb_content;
	private String sb_authr;
	private String sb_date;
	private String sb_id;
	public StarBaby() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getSb_img() {
		return sb_img;
	}

	public void setSb_img(String sb_img) {
		this.sb_img = sb_img;
	}

	public String getSb_content() {
		return sb_content;
	}

	public void setSb_content(String sb_content) {
		this.sb_content = sb_content;
	}

	public String getSb_authr() {
		return sb_authr;
	}

	public void setSb_authr(String sb_authr) {
		this.sb_authr = sb_authr;
	}

	public String getSb_date() {
		return sb_date;
	}

	public void setSb_date(String sb_date) {
		this.sb_date = sb_date;
	}

	public String getSb_id() {
		return sb_id;
	}

	public void setSb_id(String sb_id) {
		this.sb_id = sb_id;
	}

	@Override
	public String toString() {
		return "StarBaby [sb_img=" + sb_img + ", sb_content=" + sb_content + ", sb_authr=" + sb_authr + ", sb_date="
				+ sb_date + ", sb_id=" + sb_id + "]";
	}
	
}
