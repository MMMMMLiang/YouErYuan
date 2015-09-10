package com.wzt.sun.infanteducation.bean;

import java.io.Serializable;

public class News implements Serializable{
	private static final long serialVersionUID = 111111112;
	private int	N_id;//ID
	private String	N_date;// 时间
	private String	N_content;//内容
	private String	N_title;//标题
	private String	N_ctitle;//副标题
	private String N_img;// 图片
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getN_id() {
		return N_id;
	}
	public void setN_id(int n_id) {
		N_id = n_id;
	}
	public String getN_date() {
		return N_date;
	}
	public void setN_date(String n_date) {
		N_date = n_date;
	}
	public String getN_content() {
		return N_content;
	}
	public void setN_content(String n_content) {
		N_content = n_content;
	}
	public String getN_title() {
		return N_title;
	}
	public void setN_title(String n_title) {
		N_title = n_title;
	}
	public String getN_ctitle() {
		return N_ctitle;
	}
	public void setN_ctitle(String n_ctitle) {
		N_ctitle = n_ctitle;
	}
	public String getN_img() {
		return N_img;
	}
	public void setN_img(String n_img) {
		N_img = n_img;
	}
	@Override
	public String toString() {
		return "News [N_id=" + N_id + ", N_date=" + N_date + ", N_content=" + N_content + ", N_title=" + N_title
				+ ", N_ctitle=" + N_ctitle + ", N_img=" + N_img + "]";
	}
	

}
