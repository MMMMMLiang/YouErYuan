package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 个人设置
 * @author sun.ml
 *
 */
public class PersonSettingActivity extends BaseActivity implements OnClickListener{
	private ImageView iv;
	private RelativeLayout rl_user;
	private RelativeLayout rl_stu;
	private RelativeLayout rl_tea;
	
	private SharedPreferences userInfo = null;
	private boolean isStu = false;
	private boolean isTea = false;
	private boolean isLea = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_setting);
		
		initView();
	}
	
	public void initView(){
		userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		iv = (ImageView) findViewById(R.id.titlebar_per_set_btn_back);
		rl_user = (RelativeLayout) findViewById(R.id.perset_user_setting);
		rl_stu = (RelativeLayout) findViewById(R.id.perset_stu_setting);
		rl_tea = (RelativeLayout) findViewById(R.id.perset_tea_setting);
		
		isStu = userInfo.getBoolean("isParent", false);
		isTea = userInfo.getBoolean("isTeacher", false);
		isLea = userInfo.getBoolean("isLeader", false);
		
		choose();
		iv.setOnClickListener(this);
		rl_user.setOnClickListener(this);
		rl_stu.setOnClickListener(this);
		
	}
	
	private void choose(){
		if(isStu == true && isTea == false){
			// 是家长
			rl_tea.setVisibility(View.GONE);
		}else if (isStu == false && isTea == true) {
			// 是老师
			rl_stu.setVisibility(View.GONE);
		}else if(isStu == false && isTea == false && isLea == true){
			rl_tea.setVisibility(View.GONE);
			rl_stu.setVisibility(View.GONE);
		}
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
		case R.id.perset_tea_setting:
			mIntent.setClass(this, SettingTeaActivity.class);
			startActivity(mIntent);
			break;

		default:
			break;
		}
		
	}
}
