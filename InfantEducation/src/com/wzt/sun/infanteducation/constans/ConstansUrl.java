package com.wzt.sun.infanteducation.constans;

public class ConstansUrl {
	private static final String IP = "192.168.1.101";
	
	//登录的URL
	public static final String loginUrl(){
		String url = "http://"+IP+"/SchoolManage/admin/login";
		return url;
	}
	
	/**
	 * 请求课程表的URL
	 */
	public static final String SYLLURL = "http://192.168.1.120:8080/SchoolManage/Syll/getJsyll?cid=";
		

}
