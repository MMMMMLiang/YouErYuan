package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.fragment.InformFragment;
import com.wzt.sun.infanteducation.fragment.InteractionFragment;
import com.wzt.sun.infanteducation.fragment.KindergartenFragment;
import com.wzt.sun.infanteducation.fragment.LeaveFragment;
import com.wzt.sun.infanteducation.fragment.LogBookFragment;
import com.wzt.sun.infanteducation.fragment.MeFragment;
import com.wzt.sun.infanteducation.fragment.ParadiseFragment;
import com.wzt.sun.infanteducation.fragment.StudentInformationFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
	//fragment管理类
	private FragmentManager fm;
	//fragment集合
	private Fragment[] fragments = new Fragment[3];

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
		Intent mIntent = new Intent();
		mIntent.setClass(DynamicActivity.this, DynamicInfoActivity.class);
		Bundle mBundle = new Bundle();
		switch (view.getId()) {
		// 学生信息
		case R.id.dy_stu_information:
			//BaseApp.getInstance().showToast("学生信息");
			mBundle.putInt("Fragment", 1);
			mIntent.putExtras(mBundle);
			startActivity(mIntent);
			break;
		// 请假申明
		case R.id.dy_qingjia_information:
			//BaseApp.getInstance().showToast("请假申明");
			mBundle.putInt("Fragment", 2);
			mIntent.putExtras(mBundle);
			startActivity(mIntent);
			break;
		// 考勤统计
		case R.id.dy_kaoqin_information:
			//BaseApp.getInstance().showToast("考勤统计");
			mBundle.putInt("Fragment", 3);
			mIntent.putExtras(mBundle);
			startActivity(mIntent);
			break;

		default:
			break;
		}
	}
	
}
