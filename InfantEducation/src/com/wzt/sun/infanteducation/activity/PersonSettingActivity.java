package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PersonSettingActivity extends BaseActivity implements OnClickListener{
	private ImageView iv;
	private RelativeLayout rl_user;
	private RelativeLayout rl_stu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_setting);
		
		initView();
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_per_set_btn_back);
		rl_user = (RelativeLayout) findViewById(R.id.perset_user_setting);
		rl_stu = (RelativeLayout) findViewById(R.id.perset_stu_setting);
		
		iv.setOnClickListener(this);
		rl_user.setOnClickListener(this);
		rl_stu.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View view) {
		Intent mIntent = new Intent();
		switch (view.getId()) {
		case R.id.titlebar_per_set_btn_back:
			PersonSettingActivity.this.finish();
			break;
		case R.id.perset_user_setting:
			mIntent.setClass(this, SettingUserActivity.class);
			startActivity(mIntent);
			break;
		case R.id.perset_stu_setting:
			mIntent.setClass(this, SettingStuActivity.class);
			startActivity(mIntent);
			break;

		default:
			break;
		}
		
	}
}
