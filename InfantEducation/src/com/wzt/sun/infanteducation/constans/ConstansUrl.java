package com.wzt.sun.infanteducation.constans;

public class ConstansUrl {
	private static final String IP = "192.168.1.141:8080";
	private static final String IP2 = "192.168.1.129";

	//登录的URL
	public static final String loginUrl(){
		String url = "http://"+IP+"/SchoolManage/admin/LoginApp";
		return url;
	}

	/**
	 * 请求头像的URL
	 */
	public static final String getHeadnUrl(String str){
		String url = "http://"+IP+str;
		return url;
	}

	/**
	 * 上传头像的URL
	 */
	public static final String POSTHEAD = "http://"+IP+"/SchoolManage/Teacher/updatephoto";

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
	 * 请求全部通知通告的URL
	 */
	public static final String GETALLINFORMURL = "http://"+IP2+"/SchoolManage/Notice/select";
	
	/**
	 * 院长请求全部通知通告的URL
	 */
	public static final String GETALLINFORMURLTOLEA = "http://"+IP2+"/SchoolManage/Notice/listbysid";
	
	/**
	 * 教师请求全部通知通告的URL
	 */
	public static final String GETALLINFORMURLTOTEA = "http://"+IP2+"/SchoolManage/Notice/listbyid";
	
	/**
	 * 学生请求全部通知通告的URL
	 */
	public static final String GETALLINFORMURLTOSTU = "http://"+IP2+"/SchoolManage/Notice/listbycid";

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
	 * 园长信箱的URL
	 */
	public static final String POSTYIJIAN = "http://"+IP2+"/SchoolManage/Feed/AddFb";

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
	public static final String GETALLCLS = "http://"+IP2+"/SchoolManage/Class/list1";

	/**
	 * 根据班级id获取论坛信息的URL
	 */
	public static final String GETALLINTER = "http://"+IP+"/SchoolManage/Theme/getJthebyid?id=";

	/**
	 * 根据帖子id点赞的URL
	 */
	public static final String GETDIANZAN(int id, String ij){
		return "http://"+IP+"/SchoolManage/Theme/dianzan?id="+id+"&ij="+ij;
	}

	/**
	 * 根据教师id修改个人信息URL
	 */
	public static final String POSTTEAINFO = "http://"+IP+"/SchoolManage/Teacher/AppUpdateTeacher";

	/**
	 * 反馈意见URL
	 */
	public static final String POSTTOYUANZHANG = "http://"+IP2+"/SchoolManage/Ret/setReturn";

	/**
	 * 学生信息设置URL
	 */
	public static final String POSTSTUINFO = "http://"+IP2+"/SchoolManage/student/updataApp";

	/**
	 * 修改密码URL
	 */
	public static final String POSTMIMA = "http://"+IP2+"/SchoolManage/admin/updataPwdById";


	/**
	 * 乐园风采URL
	 */
	public static final String GETSCHOOLNEWS = "http://"+IP+"/SchoolManage/News/getNews";

	/**
	 * 宝宝作品URL
	 */
	public static final String GETBABYWORKS = "http://"+IP+"/SchoolManage/Works/SeleWork";

	/**
	 * 园长查看家长信的URL
	 */
	public static final String GETPARENTSLATER = "http://"+IP2+"/SchoolManage/Feed/listFb1";
	
	/**
	 * 园长删除家长信的URL
	 */
	public static final String POSTLATERDELETE = "http://"+IP2+"/SchoolManage/Feed/deleteFb";
	
	/**
	 * 园长删除通知通告的URL
	 */
	public static final String POSTINFORM = "http://"+IP2+"/SchoolManage/Notice/insertNocbyid";

}
