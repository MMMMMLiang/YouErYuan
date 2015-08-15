package com.wzt.sun.infanteducation.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wzt.sun.infanteducation.bean.Education;
import com.wzt.sun.infanteducation.bean.Food;
import com.wzt.sun.infanteducation.bean.Homework;
import com.wzt.sun.infanteducation.bean.Inform;
import com.wzt.sun.infanteducation.bean.Recipe;
import com.wzt.sun.infanteducation.bean.StarBaby;
import com.wzt.sun.infanteducation.bean.Syllabus;

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
				
				mSyllabus.setC_id(mObject.optString("c_id"));
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
				/*private int h_id; 
				private String h_title;
				private int h_authr;
				private String h_content;
				private String h_date;
				private int c_id;*/
				
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

}
