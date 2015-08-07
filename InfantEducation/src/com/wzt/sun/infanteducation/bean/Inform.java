package com.wzt.sun.infanteducation.bean;

import java.io.Serializable;

public class Inform implements Serializable{

	private int n_author;
	private String n_content;
	private String n_date;
	private int n_id;
	private String n_title;
	private String n_type;

	public Inform() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getN_author() {
		return n_author;
	}

	public void setN_author(int n_author) {
		this.n_author = n_author;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public String getN_date() {
		return n_date;
	}

	public void setN_date(String n_date) {
		this.n_date = n_date;
	}

	public int getN_id() {
		return n_id;
	}

	public void setN_id(int n_id) {
		this.n_id = n_id;
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_type() {
		return n_type;
	}

	public void setN_type(String n_type) {
		this.n_type = n_type;
	}

	@Override
	public String toString() {
		return "Inform [n_author=" + n_author + ", n_content=" + n_content + ", n_date=" + n_date + ", n_id=" + n_id
				+ ", n_title=" + n_title + ", n_type=" + n_type + "]";
	}

}
