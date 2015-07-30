package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 反馈院长页面
 * 
 * @author sun
 * 
 */
public class FeedbackActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);

	}

	public void btnClick(View view) {
		switch (view.getId()) {
		// 点击返回箭头
		case R.id.titlebar_feet_btn_back:
			FeedbackActivity.this.finish();
			break;
		// 点击确定
		case R.id.feed_bar_btn:
			BaseApp.getInstance().showToast("点击了确定");
			break;

		default:
			break;
		}
	}
}
