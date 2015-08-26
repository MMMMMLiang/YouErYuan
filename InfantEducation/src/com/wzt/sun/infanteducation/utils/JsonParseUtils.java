package com.wzt.sun.infanteducation.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wzt.sun.infanteducation.bean.Attendance;
import com.wzt.sun.infanteducation.bean.Classes;
import com.wzt.sun.infanteducation.bean.Education;
import com.wzt.sun.infanteducation.bean.Food;
import com.wzt.sun.infanteducation.bean.Homework;
import com.wzt.sun.infanteducation.bean.Inform;
import com.wzt.sun.infanteducation.bean.ItemEntity;
import com.wzt.sun.infanteducation.bean.Recipe;
import com.wzt.sun.infanteducation.bean.StarBaby;
import com.wzt.sun.infanteducation.bean.Student;
import com.wzt.sun.infanteducation.bean.Syllabus;
import com.wzt.sun.infanteducation.bean.Teacher;
import com.wzt.sun.infanteducation.bean.User;

public class JsonParseUtils {
	
	/**
	 * JSON解析课程表返回的数据
	 * @param data
	 * @return
	 */
	public static List<Syllabus> parseJsonSyllabus(String data){
		List<Syllabus> lists = new ArrayList<Syllabus>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Syllabus mSyllabus = new Syllabus();
				
				mSyllabus.setC_id(mObject.optInt("c_id"));
				mSyllabus.setC_name(mObject.optString("c_name"));
				mSyllabus.setSy_am(mObject.optString("sy_am"));
				mSyllabus.setSy_authr(mObject.optString("sy_authr"));
				mSyllabus.setSy_date(mObject.optString("sy_date"));
				mSyllabus.setSy_id(mObject.optString("sy_id"));
				mSyllabus.setSy_pm(mObject.optString("sy_pm"));
				mSyllabus.setSy_uploaddate(mObject.optString("sy_uploaddate"));
				mSyllabus.setSy_week(mObject.optString("sy_week"));
				
				lists.add(mSyllabus);
				
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * JSON解析通知
	 * @param data
	 * @return
	 */
	public static List<Inform> parseJsonInform(String data){
		List<Inform> lists = new ArrayList<Inform>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Inform mInform = new Inform();
				
				mInform.setN_author(mObject.optInt("n_author"));
				mInform.setN_content(mObject.optString("n_content"));
				mInform.setN_date(mObject.optString("n_date"));
				mInform.setN_id(mObject.optInt("n_id"));
				mInform.setN_title(mObject.optString("n_title"));
				mInform.setN_type(mObject.optString("n_type"));
				
				lists.add(mInform);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析明星宝宝
	 * @param data
	 * @return
	 */
	public static List<StarBaby> parseJsonStarBaby(String data){
		List<StarBaby> lists = new ArrayList<StarBaby>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				StarBaby mStarBaby = new StarBaby();
				
				mStarBaby.setSb_authr(mObject.optString("sb_authr"));
				mStarBaby.setSb_content(mObject.optString("sb_content"));
				mStarBaby.setSb_date(mObject.optString("sb_date"));
				mStarBaby.setSb_id(mObject.optString("sb_id"));
				mStarBaby.setSb_img(mObject.optString("sb_img"));
				
				lists.add(mStarBaby);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析食物
	 * @param data
	 * @return
	 */
	public static List<Food> parseJsonFood(String data){
		List<Food> lists = new ArrayList<Food>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Food mFood = new Food();
				
				mFood.setFo_authr(mObject.optInt("fo_authr"));
				mFood.setFo_date(mObject.optString("fo_date"));
				mFood.setFo_id(mObject.optInt("fo_id"));
				mFood.setFo_img(mObject.optString("fo_img"));
				mFood.setFo_name(mObject.optString("fo_name"));
				
				lists.add(mFood);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析食谱
	 * @param data
	 * @return
	 */
	public static List<Recipe> parseJsonRecipe(String data){
		List<Recipe> lists = new ArrayList<Recipe>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Recipe mRecipe = new Recipe();
				
				// 中午食谱
				mRecipe.setRe_af(mObject.optString("re_af"));
				// 下午加餐
				mRecipe.setRe_afdown(mObject.optString("re_afdown"));
				/*// 午餐
				mRecipe.setRe_am(mObject.optString("re_am"));*/
				// 上午加餐
				mRecipe.setRe_amup(mObject.optString("re_amup"));
				mRecipe.setRe_authr(mObject.optInt("re_authr"));
				mRecipe.setRe_date(mObject.optString("re_date"));
				mRecipe.setRe_id(mObject.optInt("re_id"));
				// 早餐
				mRecipe.setRe_mr(mObject.optString("re_mr"));
				// 晚餐
				mRecipe.setRe_ni(mObject.optString("re_ni"));
				mRecipe.setRe_uploaddate(mObject.optString("re_uploaddate"));
				mRecipe.setRe_week(mObject.optString("re_week"));
				
				lists.add(mRecipe);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析教育教学
	 * @param data
	 * @return
	 */
	public static List<Education> parseJsonEdu(String data){
		List<Education> lists = new ArrayList<Education>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Education mEducation = new Education();
				
				mEducation.setSy_content(mObject.optString("sy_content"));
				mEducation.setSy_date(mObject.optString("sy_date"));
				mEducation.setSy_id(mObject.optInt("sy_id"));
				mEducation.setSy_img(mObject.optString("sy_img"));
				mEducation.setSy_title(mObject.optString("sy_title"));
				mEducation.setSy_type(mObject.optInt("sy_type"));
				
				lists.add(mEducation);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析家庭作业
	 * @param data
	 * @return
	 */
	public static List<Homework> parseJsonHomeWork(String data){
		List<Homework> lists = new ArrayList<Homework>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Homework mHomework = new Homework();
				
				mHomework.setC_id(mObject.optInt("c_id"));
				mHomework.setH_authr(mObject.optInt("sy_date"));
				mHomework.setH_content(mObject.optString("h_content"));
				mHomework.setH_date(mObject.optString("h_date"));
				mHomework.setH_id(mObject.optInt("h_id"));
				mHomework.setH_title(mObject.optString("h_title"));
				
				lists.add(mHomework);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析考勤表
	 * @param data
	 * @return
	 */
	public static List<Attendance> parseJsonAttendance(String data){
		List<Attendance> lists = new ArrayList<Attendance>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Attendance mAttendance = new Attendance();
				mAttendance.setC_id(mObject.optInt("c_id"));
				mAttendance.setA_date(mObject.optString("a_date"));
				mAttendance.setA_am(mObject.optString("a_am"));
				mAttendance.setA_ID(mObject.optInt("a_ID"));
				mAttendance.setA_pm(mObject.optString("a_pm"));
				mAttendance.setA_rate(mObject.optString("a_rate"));
				mAttendance.setA_type(mObject.optString("a_type"));
				mAttendance.setSt_id(mObject.optInt("st_id"));
				
				lists.add(mAttendance);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析学生表
	 * @param data
	 * @return
	 */
	public static List<Student> parseJsonStudents(String data){
		List<Student> lists = new ArrayList<Student>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Student mStudent = new Student();
				
				mStudent.setSt_id(mObject.optInt("st_id"));
				mStudent.setC_id(mObject.optInt("c_id"));
				mStudent.setSt_sex(mObject.optString("st_sex"));
				mStudent.setSt_volk(mObject.optString("st_volk"));
				mStudent.setSt_birthday(mObject.optString("st_birthday"));
				mStudent.setSt_date(mObject.optString("st_date"));
				mStudent.setSt_card(mObject.optString("st_card"));
				mStudent.setSt_address(mObject.optString("st_address"));
				mStudent.setSt_father(mObject.optString("st_father"));
				mStudent.setSt_fcard(mObject.optString("st_fcard"));
				mStudent.setSt_mother(mObject.optString("st_mother"));
				mStudent.setSt_mcard(mObject.optString("st_mcard"));
				mStudent.setSt_health(mObject.optString("st_health"));
				mStudent.setSt_hremarks(mObject.optString("st_hremarks"));
				mStudent.setSt_shuttlecard(mObject.optString("st_shuttlecard"));
				mStudent.setSt_shuttle(mObject.optString("st_shuttle"));
				mStudent.setSt_graduated(mObject.optString("st_graduated"));
				mStudent.setSt_photo(mObject.optString("st_photo"));
				mStudent.setSt_name(mObject.optString("st_name"));
				
				
				lists.add(mStudent);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析用户表
	 * @param data
	 * @return
	 */
	public static List<User> parseJsonUser(String data){
		List<User> lists = new ArrayList<User>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				User mUser = new User();
				
				mUser.setVid(mObject.optInt("vid"));
				mUser.setVsf(mObject.optString("vsf"));
				mUser.setVsfname(mObject.optString("vsfname"));
				mUser.setUser(mObject.optString("user"));
				mUser.setPassword(mObject.optString("password"));
				mUser.setName(mObject.optString("name"));
				mUser.setPhone(mObject.optString("phone"));
				mUser.setRegisterdate(mObject.optString("registerdate"));
				mUser.setState(mObject.optString("state"));
				mUser.setIdentity(mObject.optString("identity"));
				mUser.setEmail(mObject.optString("email"));
				mUser.setAddress(mObject.optString("address"));
				mUser.setAppointmenttime(mObject.optString("appointmenttime"));
				mUser.setRemarks1(mObject.optString("remarks1"));
				mUser.setRemarks2(mObject.optString("remarks2"));
				mUser.setJifen(mObject.optInt("jifen"));
				
				lists.add(mUser);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析教师表
	 * @param data
	 * @return
	 */
	public static List<Teacher> parseJsonTeacher(String data){
		List<Teacher> lists = new ArrayList<Teacher>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Teacher mTeacher = new Teacher();
								
				mTeacher.setT_id(mObject.optInt("t_id"));
				mTeacher.setT_name(mObject.optString("t_name"));
				mTeacher.setT_sex(mObject.optString("t_sex"));
				mTeacher.setT_lv(mObject.optString("t_lv"));
				mTeacher.setT_date(mObject.optString("t_date"));
				mTeacher.setT_volk(mObject.optString("t_volk"));
				mTeacher.setT_job(mObject.optString("t_job"));
				mTeacher.setT_title(mObject.optString("t_title"));
				mTeacher.setT_phone(mObject.optString("t_phone"));
				mTeacher.setT_card(mObject.optString("t_card"));
				mTeacher.setT_address(mObject.optString("t_address"));
				mTeacher.setT_we(mObject.optString("t_we"));
				mTeacher.setT_img(mObject.optString("t_img"));
				mTeacher.setT_type(mObject.optString("t_type"));
				mTeacher.setC_id(mObject.optInt("c_id"));
				
				lists.add(mTeacher);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析班级表
	 * @param data
	 * @return
	 */
	public static List<Classes> parseJsonClasses(String data){
		List<Classes> lists = new ArrayList<Classes>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				Classes mClasses = new Classes();
								
				mClasses.setC_id(mObject.optInt("cid"));
				mClasses.setC_name(mObject.optString("cname"));
				mClasses.setS_id(mObject.optInt("sid"));
				mClasses.setC_head(mObject.optString("chead"));
				mClasses.setC_type(mObject.optString("ctype"));
				mClasses.setC_count(mObject.optInt("ccount"));
				mClasses.setC_phone1(mObject.optString("cphone1"));
				mClasses.setS_phone2(mObject.optString("sphone2"));
				mClasses.setC_remarks(mObject.optString("cremarks"));
				
				lists.add(mClasses);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON解析论坛表
	 * @param data
	 * @return
	 */
	public static List<ItemEntity> parseJsonInteraction(String data){
		List<ItemEntity> lists = new ArrayList<ItemEntity>();
		
		try {
			JSONArray mArray = new JSONArray(data);
			for (int i = 0; i < mArray.length(); i++) {
				JSONObject mObject = mArray.optJSONObject(i);
				ItemEntity mItemEntity = new ItemEntity();
								
				mItemEntity.setTh_image(mObject.optString("img"));
				mItemEntity.setTh_name(mObject.optString("name"));
				mItemEntity.setTh_Vp(mObject.optString("th_Vp"));
				mItemEntity.setTh_accessory(mObject.optString("th_accessory"));
				mItemEntity.setTh_authr(mObject.optInt("th_authr"));
				mItemEntity.setTh_classes(mObject.optInt("th_classes"));
				mItemEntity.setTh_content(mObject.optString("th_content"));
				mItemEntity.setTh_date(mObject.optString("th_date"));
				mItemEntity.setTh_id(mObject.optInt("th_id"));
				mItemEntity.setTh_num(mObject.optInt("th_num"));
				mItemEntity.setTh_zan(mObject.optInt("th_zan"));
				
				lists.add(mItemEntity);
			}
			return lists;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
