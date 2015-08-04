package com.wzt.sun.infanteducation.constans;

import java.util.ArrayList;
import java.util.HashMap;

import com.wzt.sun.infanteducation.R;

public class ConstantsConfig {
	public static final String APP_CACHE = "kindergarten";
	public static final String SYS_CACHE = "com.wzt.kindergarten";
	public static final String IMAGE_CACHE = "image";
	// 判断是否第一次运行
	public static final String SHAREDPREFERENCES_NAME = "prefs_firstrun";
	// 判断登录状态
	public static final String SHAREDPREFERENCES_LOGIN = "prefs_login";
	
	// 身份1加载的功能模块
	// "反馈园长","教学计划","教育咨询","今日食谱","课程安排","明星宝宝","评价图标","校园简介","学生动态"
	public static final ArrayList<HashMap<String, Object>> loadId1Lists() {
		ArrayList<HashMap<String, Object>> id1Lists = new ArrayList<HashMap<String, Object>>();
		String texts[] = new String[] { "校园简介", "课程安排", "学生动态", "评价老师", "明星宝宝",
				"今日食谱", "教育咨询", "教学计划", "反馈园长" };
		int images[] = new int[] { R.drawable.gv_kg_xiaoyuanjianjie,
				R.drawable.gv_kg_kechenganpai,
				R.drawable.gv_kg_xueshengdongtai,
				R.drawable.gv_kg_pingjiatubiao,
				R.drawable.gv_kg_mingxingbaobao, 
				R.drawable.gv_kg_jinrishipu,
				R.drawable.gv_kg_jiaoyucicun, 
				R.drawable.gv_kg_jiaoxuejihua,
				R.drawable.gv_kg_fankuiyuanzhang };
		
		for (int i = 0; i < 9; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", images[i]);
            map.put("itemText", texts[i]);
            id1Lists.add(map);
        }

		return id1Lists;
	}
	
	/**
	 * MeFragment添加菜单
	 * @return
	 */
	public static final ArrayList<HashMap<String, Object>> loadMyMenu(){
		ArrayList<HashMap<String, Object>> id1Lists = new ArrayList<HashMap<String, Object>>();
		String texts[] = new String[] { "个人设置", "邀请家人", "使用说明", "意见反馈"};
		int[] images = new int[] {R.drawable.icon_me_personal_settings,
				R.drawable.icon_me_invite_family,
				R.drawable.icon_me_use_help,
				R.drawable.icon_me_return_message};
		
		for (int i = 0; i < texts.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", images[i]);
            map.put("itemText", texts[i]);
            id1Lists.add(map);
        }
		return id1Lists;
	}
	
}
