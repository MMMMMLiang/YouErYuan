package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 这是学生动态页面
 * 
 * @author sun
 * 
 */
public class DynamicActivity extends BaseActivity {
	private ImageView titlebar_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic);

		titlebar_back = (ImageView) findViewById(R.id.titlebar_dynamic_btn_back);

		titlebar_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				DynamicActivity.this.finish();

			}
		});
	}

	public void rlClick(View view) {
		switch (view.getId()) {
		// 学生信息
		case R.id.dy_stu_information:
			BaseApp.getInstance().showToast("学生信息");
			break;
		// 请假申明
		case R.id.dy_qingjia_information:
			BaseApp.getInstance().showToast("请假申明");
			break;
		// 考勤统计
		case R.id.dy_kaoqin_information:
			BaseApp.getInstance().showToast("考勤统计");
			break;

		default:
			break;
		}
	}

}
