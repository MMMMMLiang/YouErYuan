package com.wzt.sun.infanteducation.bean;

public class Classes {
	private int c_id;
	private String c_name;
	private int s_id;
	private String c_head;
	private String c_type;
	private int c_count;
	private String c_phone1;
	private String s_phone2;
	private String c_remarks;
	public Classes() {
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
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getC_head() {
		return c_head;
	}
	public void setC_head(String c_head) {
		this.c_head = c_head;
	}
	public String getC_type() {
		return c_type;
	}
	public void setC_type(String c_type) {
		this.c_type = c_type;
	}
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	public String getC_phone1() {
		return c_phone1;
	}
	public void setC_phone1(String c_phone1) {
		this.c_phone1 = c_phone1;
	}
	public String getS_phone2() {
		return s_phone2;
	}
	public void setS_phone2(String s_phone2) {
		this.s_phone2 = s_phone2;
	}
	public String getC_remarks() {
		return c_remarks;
	}
	public void setC_remarks(String c_remarks) {
		this.c_remarks = c_remarks;
	}
	@Override
	public String toString() {
		return "Classes [c_id=" + c_id + ", c_name=" + c_name + ", s_id=" + s_id + ", c_head=" + c_head + ", c_type="
				+ c_type + ", c_count=" + c_count + ", c_phone1=" + c_phone1 + ", s_phone2=" + s_phone2 + ", c_remarks="
				+ c_remarks + "]";
	}
	
}
