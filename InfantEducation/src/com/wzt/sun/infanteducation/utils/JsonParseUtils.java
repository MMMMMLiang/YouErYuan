package com.wzt.sun.infanteducation.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

}
