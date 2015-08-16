package com.wzt.sun.infanteducation.bean;

public class Attendance {

	private int A_ID;
	private int St_id;
	private String A_date;
	private String A_am;
	private String A_pm;
	private int C_id;
	private int A_authr;
	private String A_type;
	private String A_rate;
	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getA_ID() {
		return A_ID;
	}
	public void setA_ID(int a_ID) {
		A_ID = a_ID;
	}
	public int getSt_id() {
		return St_id;
	}
	public void setSt_id(int st_id) {
		St_id = st_id;
	}
	public String getA_date() {
		return A_date;
	}
	public void setA_date(String a_date) {
		A_date = a_date;
	}
	public String getA_am() {
		return A_am;
	}
	public void setA_am(String a_am) {
		A_am = a_am;
	}
	public String getA_pm() {
		return A_pm;
	}
	public void setA_pm(String a_pm) {
		A_pm = a_pm;
	}
	public int getC_id() {
		return C_id;
	}
	public void setC_id(int c_id) {
		C_id = c_id;
	}
	public int getA_authr() {
		return A_authr;
	}
	public void setA_authr(int a_authr) {
		A_authr = a_authr;
	}
	public String getA_rate() {
		return A_rate;
	}
	public void setA_rate(String a_rate) {
		A_rate = a_rate;
	}
	public String getA_type() {
		return A_type;
	}
	public void setA_type(String a_type) {
		A_type = a_type;
	}
	@Override
	public String toString() {
		return "Attendance [A_ID=" + A_ID + ", St_id=" + St_id + ", A_date=" + A_date + ", A_am=" + A_am + ", A_pm="
				+ A_pm + ", C_id=" + C_id + ", A_authr=" + A_authr + ", A_rate=" + A_rate + "]";
	}
	
	
}
