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
	// 判断登录状态与保存用户信息
	public static final String SHAREDPREFERENCES_LOGIN = "prefs_login";
	// 保存学生或教师信息
	public static final String SHAREDPREFERENCES_USER = "prefs_user";

	// 家长加载的功能模块
	// "校园简介", "课程安排", "学生动态", "考勤统计", "明星宝宝", "今日食谱", "教育教学", "课时作业", "反馈园长"
	public static final ArrayList<HashMap<String, Object>> loadId1Lists() {
		ArrayList<HashMap<String, Object>> id1Lists = new ArrayList<HashMap<String, Object>>();
		String texts[] = new String[] { "校园简介", "乐园风采", "宝宝作品", "老师点评", "明星宝贝", "今日食谱", "课时安排", "活动专区", "园长信箱" };
		int images[] = new int[] { R.drawable.gv_kg_xiaoyuanjianjie, R.drawable.gv_kg_kechenganpai,
				R.drawable.gv_kg_xueshengdongtai, R.drawable.gv_kg_pingjiatubiao, R.drawable.gv_kg_mingxingbaobao,
				R.drawable.gv_kg_jinrishipu, R.drawable.gv_kg_jiaoyucicun, R.drawable.gv_kg_jiaoxuejihua,
				R.drawable.gv_kg_fankuiyuanzhang };

		for (int i = 0; i < 9; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			id1Lists.add(map);
		}

		return id1Lists;
	}

	// 教师加载的功能模块
	// "校园简介", "课程安排", "学生动态", "考勤统计", "明星宝宝", "今日食谱", "教育教学", "课时作业", "反馈园长"
	public static final ArrayList<HashMap<String, Object>> loadId2Lists() {
		ArrayList<HashMap<String, Object>> id1Lists = new ArrayList<HashMap<String, Object>>();
		String texts[] = new String[] { "校园简介", "乐园风采", "宝宝作品", "老师点评", "明星宝贝", "今日食谱", "我的班级", "教学计划", "考勤请假" };
		int images[] = new int[] { R.drawable.gv_kg_xiaoyuanjianjie, R.drawable.gv_kg_kechenganpai,
				R.drawable.gv_kg_xueshengdongtai, R.drawable.gv_kg_pingjiatubiao, R.drawable.gv_kg_mingxingbaobao,
				R.drawable.gv_kg_jinrishipu, R.drawable.gv_kg_jiaoyucicun, R.drawable.gv_kg_jiaoxuejihua,
				R.drawable.gv_kg_fankuiyuanzhang };

		for (int i = 0; i < 9; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			id1Lists.add(map);
		}

		return id1Lists;
	}

	// 园长加载的功能模块
	// "反馈园长","教学计划","教育咨询","今日食谱","课程安排","明星宝宝","考勤统计","校园简介","学生动态"
	public static final ArrayList<HashMap<String, Object>> loadId3Lists() {
		ArrayList<HashMap<String, Object>> id1Lists = new ArrayList<HashMap<String, Object>>();
		String texts[] = new String[] { "乐园风采", "宝贝作品", "教师信息", "幼儿信息", "明星宝贝", "今日食谱", "信息发布", "教学计划", "家长信" };
		int images[] = new int[] { R.drawable.gv_kg_xiaoyuanjianjie, R.drawable.gv_kg_kechenganpai,
				R.drawable.gv_kg_xueshengdongtai, R.drawable.gv_kg_pingjiatubiao, R.drawable.gv_kg_mingxingbaobao,
				R.drawable.gv_kg_jinrishipu, R.drawable.gv_kg_jiaoyucicun, R.drawable.gv_kg_jiaoxuejihua,
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
	 * 
	 * @return
	 */
	public static final ArrayList<HashMap<String, Object>> loadMyMenu() {
		ArrayList<HashMap<String, Object>> id1Lists = new ArrayList<HashMap<String, Object>>();
		String texts[] = new String[] { "个人设置", "请假申明", "修改密码", "使用说明", "意见反馈", "检查更新" };
		int[] images = new int[] { R.drawable.icon_me_personal_settings, R.drawable.me_fragment_leave, R.drawable.icon_me_invite_family,
				R.drawable.icon_me_use_help, R.drawable.icon_me_return_message, R.drawable.setting_update };

		for (int i = 0; i < texts.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			id1Lists.add(map);
		}
		return id1Lists;
	}
	
	/**
	 * MeFragment添加菜单(教师)
	 * 
	 * @return
	 */
	public static final ArrayList<HashMap<String, Object>> loadMyMenuToTea() {
		ArrayList<HashMap<String, Object>> id1Lists = new ArrayList<HashMap<String, Object>>();
		String texts[] = new String[] { "个人设置", "班级通告", "修改密码", "使用说明", "意见反馈", "检查更新" };
		int[] images = new int[] { R.drawable.icon_me_personal_settings, R.drawable.me_fragment_leave, R.drawable.icon_me_invite_family,
				R.drawable.icon_me_use_help, R.drawable.icon_me_return_message, R.drawable.setting_update };

		for (int i = 0; i < texts.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			id1Lists.add(map);
		}
		return id1Lists;
	}

}
