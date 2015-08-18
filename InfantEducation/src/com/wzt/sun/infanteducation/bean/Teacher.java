package com.wzt.sun.infanteducation.bean;

public class Teacher {
	private int t_id;//教师唯一标示(主键)
	private	String t_name;//教师姓名
	private	String	t_sex;//教师性别
	private	String	t_lv;//学历
	private	String	t_date;//任教时间
	private	String	t_volk;//民族
	private	String	t_job;//教师行政职务
	private	String	t_title;//教师职称
	private	String	t_phone;//教师电话
	private	String	t_card;//教师身份证号
	private	String	t_address;//教师住址
	private	String	t_we;//教师履历
	private	String	t_img;//教师照片
	private	String	t_type;//教师类型
	private	int	c_id;//所属班级
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_sex() {
		return t_sex;
	}
	public void setT_sex(String t_sex) {
		this.t_sex = t_sex;
	}
	public String getT_lv() {
		return t_lv;
	}
	public void setT_lv(String t_lv) {
		this.t_lv = t_lv;
	}
	public String getT_date() {
		return t_date;
	}
	public void setT_date(String t_date) {
		this.t_date = t_date;
	}
	public String getT_volk() {
		return t_volk;
	}
	public void setT_volk(String t_volk) {
		this.t_volk = t_volk;
	}
	public String getT_job() {
		return t_job;
	}
	public void setT_job(String t_job) {
		this.t_job = t_job;
	}
	public String getT_title() {
		return t_title;
	}
	public void setT_title(String t_title) {
		this.t_title = t_title;
	}
	public String getT_phone() {
		return t_phone;
	}
	public void setT_phone(String t_phone) {
		this.t_phone = t_phone;
	}
	public String getT_card() {
		return t_card;
	}
	public void setT_card(String t_card) {
		this.t_card = t_card;
	}
	public String getT_address() {
		return t_address;
	}
	public void setT_address(String t_address) {
		this.t_address = t_address;
	}
	public String getT_we() {
		return t_we;
	}
	public void setT_we(String t_we) {
		this.t_we = t_we;
	}
	public String getT_img() {
		return t_img;
	}
	public void setT_img(String t_img) {
		this.t_img = t_img;
	}
	public String getT_type() {
		return t_type;
	}
	public void setT_type(String t_type) {
		this.t_type = t_type;
	}
	
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	@Override
	public String toString() {
		return "Teacher [t_id=" + t_id + ", t_name=" + t_name + ", t_sex=" + t_sex + ", t_lv=" + t_lv + ", t_date="
				+ t_date + ", t_volk=" + t_volk + ", t_job=" + t_job + ", t_title=" + t_title + ", t_phone=" + t_phone
				+ ", t_card=" + t_card + ", t_address=" + t_address + ", t_we=" + t_we + ", t_img=" + t_img
				+ ", t_type=" + t_type + ", c_id=" + c_id + "]";
	}
	

}
