package com.wzt.sun.infanteducation.activity;

import android.app.Activity;

import com.wzt.sun.infanteducation.utils.AppManager;

public class BaseActivity extends Activity {
	
	public void init(){
		AppManager.getAppManager().addActivity(this);
		initView();
	}

	/**
	 * 加载控件
	 */
	public void initView() {
		
	}
	
	/**
	 * 加载数据
	 */
	public void loadData(){
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		AppManager.getAppManager().finishActivity(this);
	}
}
