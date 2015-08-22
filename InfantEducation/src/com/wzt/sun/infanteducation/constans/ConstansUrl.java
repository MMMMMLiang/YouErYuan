package com.wzt.sun.infanteducation.constans;

public class ConstansUrl {
	private static final String IP = "192.168.1.110:8080";

	//登录的URL
	public static final String loginUrl(){
		String url = "http://"+IP+"/SchoolManage/vipuser/login";
		return url;
	}
	/**
	 * 请求学生信息的URL
	 */
	public static final String GETSTUINFO = "http://"+IP+"/SchoolManage/student/listjson";

	/**
	 * 请求课程表的URL
	 */
	public static final String SYLLURL = "http://"+IP+"/SchoolManage/Syll/getJsyll?cid=";

	/**
	 * 请求通知通告的URL
	 */
	public static final String INFORMURL = "http://"+IP+"/SchoolManage/Notice/gettypebyNoc?type=";

	/**
	 * 上传请假条的URL
	 */
	public static final String POSTLEAVE = "http://"+IP+"/SchoolManage/Lev/addLv";

	/**
	 * 请求明星宝宝的URL
	 */
	public static final String GETSTAR = "http://"+IP+"/SchoolManage/Str/getstr";

	/**
	 * 请求食物的URL
	 */
	public static final String GETFOOD = "http://"+IP+"/SchoolManage/Food/getfd";

	/**
	 * 请求食物的URL
	 */
	public static final String GETRECIPE = "http://"+IP+"/SchoolManage/Rec/getRec";
	
	/**
	 * 请求教育教学的URL
	 */
	public static final String GETEDUCATION = "http://"+IP+"/SchoolManage/Sty/getSty";
	
	/**
	 * 请求教育教学的URL
	 */
	public static final String GETHOMEWORK = "http://"+IP+"/SchoolManage/Home/getHomewk?id=";
	
	/**
	 * 请求学生考勤的URL
	 */
	public static final String GETSTUATTEND = "http://"+IP+"/SchoolManage/Atted/getAte?sid=";
	
	/**
	 * 请求学生考勤的URL
	 */
	public static final String POSTYIJIAN = "http://"+IP+"/SchoolManage/Feed/AddFb";

	/**
	 * 请求老师姓名的URL
	 */
	public static final String GETTEANAME = "http://"+IP+"/SchoolManage/Teacher/getTea?id=";
	
	/**
	 * 发送评价老师的URL
	 */
	public static final String POSTTEANAME = "http://"+IP+"/SchoolManage/Ass/insert";
	
	/**
	 * 获取学生信息的URL
	 */
	public static final String GETSTUDENTSINFO = "http://"+IP+"/SchoolManage/student/getStub?st_id=";
	
	/**
	 * 获取教师信息的URL
	 */
	public static final String GETTEACHERSINFO = "http://"+IP+"/SchoolManage/Teacher/getTeabyid?T_id=";
	
	/**
	 * 获取花名册信息的URL
	 */
	public static final String GETALLSTU = "http://"+IP+"/SchoolManage/student/listjson?cid=";
	
	/**
	 * 获取所有教师信息的URL
	 */
	public static final String GETALLTEA = "http://"+IP+"/SchoolManage/Teacher/getteaJ";
	
	/**
	 * 获取所有班级信息的URL
	 */
	public static final String GETALLCLS = "http://"+IP+"/SchoolManage/Class/getClsJ";
	
}
