package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 这是学生动态页面
 * 
 * @author sun
 * 
 */
public class DynamicActivity extends FragmentActivity {

	private ImageView titlebar_back;
	private SharedPreferences userInfo = null;
	private boolean isStu = false;
	private boolean isTea = false;
	
	private RelativeLayout rl_stu_info;
	private RelativeLayout rl_evaluate;
	private RelativeLayout rl_tea_info;
	private RelativeLayout rl_all_stu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic);
		
		userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		titlebar_back = (ImageView) findViewById(R.id.titlebar_dynamic_btn_back);
		
		rl_stu_info = (RelativeLayout) findViewById(R.id.dy_stu_information);
		rl_evaluate = (RelativeLayout) findViewById(R.id.dy_kaoqin_information);
		rl_tea_info = (RelativeLayout) findViewById(R.id.dy_tea_information);
		rl_all_stu = (RelativeLayout) findViewById(R.id.dy_allstu_information);
		
		isStu = userInfo.getBoolean("isParent", false);
		isTea = userInfo.getBoolean("isTeacher", false);
		
		choose();
		titlebar_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				DynamicActivity.this.finish();

			}
		});
	}
	
	private void choose(){
		if(isStu == true && isTea == false){
			// 是家长
			rl_tea_info.setVisibility(View.GONE);
			rl_all_stu.setVisibility(View.GONE);
		}else if (isStu == false && isTea == true) {
			rl_stu_info.setVisibility(View.GONE);
			rl_evaluate.setVisibility(View.GONE);
		}
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
			// 评价老师
		case R.id.dy_kaoqin_information:
			intent.setClass(this, EvaluateActivity.class);
			startActivity(intent);
			break;
			// 教师信息
		case R.id.dy_tea_information:
			intent.setClass(this, TeacherInformationActivity.class);
			startActivity(intent);
			break;
			// 花名册
		case R.id.dy_allstu_information:
			intent.setClass(this, AllStuInformationActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
