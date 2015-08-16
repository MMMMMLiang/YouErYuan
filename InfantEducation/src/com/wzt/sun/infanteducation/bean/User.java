package com.wzt.sun.infanteducation.bean;

public class User {
	
	private int vid;
	private String vsf;
	private String vsfname;
	private String user;
	private String password;
	private String name;
	private String phone;
	private String registerdate;
	private String state;
	private String identity;
	private String email;
	private String address;
	private String appointmenttime;
	private String remarks1;
	private String remarks2;
	private int jifen;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVsf() {
		return vsf;
	}

	public void setVsf(String vsf) {
		this.vsf = vsf;
	}

	public String getVsfname() {
		return vsfname;
	}

	public void setVsfname(String vsfname) {
		this.vsfname = vsfname;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAppointmenttime() {
		return appointmenttime;
	}

	public void setAppointmenttime(String appointmenttime) {
		this.appointmenttime = appointmenttime;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}

	public int getJifen() {
		return jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	@Override
	public String toString() {
		return "User [vid=" + vid + ", vsf=" + vsf + ", vsfname=" + vsfname + ", user=" + user + ", password="
				+ password + ", name=" + name + ", phone=" + phone + ", registerdate=" + registerdate + ", state="
				+ state + ", identity=" + identity + ", email=" + email + ", address=" + address + ", appointmenttime="
				+ appointmenttime + ", remarks1=" + remarks1 + ", remarks2=" + remarks2 + ", jifen=" + jifen + "]";
	}
	
}
