package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 这是学生动态页面
 * 
 * @author sun
 * 
 */
public class DynamicActivity extends FragmentActivity {

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
		Intent intent = new Intent();
		switch (view.getId()) {
		// 学生信息
		case R.id.dy_stu_information:
			intent.setClass(this, StudentInformationActivity.class);
			startActivity(intent);
			break;
		// 请假申明
		case R.id.dy_qingjia_information:
			intent.setClass(this, LeaveActivity.class);
			startActivity(intent);
			break;
		// 考勤统计
		case R.id.dy_kaoqin_information:
			intent.setClass(this, StudentInformationActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
