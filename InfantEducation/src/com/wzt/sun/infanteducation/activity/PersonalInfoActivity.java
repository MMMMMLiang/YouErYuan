package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalInfoActivity extends BaseActivity {
	
	private ImageView btn_goBack;
	private TextView text_name;
	private TextView text_sex;
	private TextView text_phone;
	
	private Handler mHamdle = new Handler(){
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 0x0006:
				
				break;

			default:
				break;
			}
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		
		btn_goBack = (ImageView) findViewById(R.id.titlebar_personal_info_btnback);
		initView();
		loadData();
		btn_goBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				PersonalInfoActivity.this.finish();
				
			}
		});
	}
	
	public void initView() {
		
		text_name = (TextView) findViewById(R.id.personal_t_name);
		text_sex = (TextView) findViewById(R.id.personal_t_sex);
		text_phone = (TextView) findViewById(R.id.personal_t_phone);
	}
	
	public void loadData() {
		
		text_name.setText("姓        名：");
		text_sex.setText("性        别：");
		text_phone.setText("联系方式：");
		mHamdle.sendEmptyMessage(0x0006);
		
	}
	
	public void ivClick(View view){
		switch (view.getId()) {
		case R.id.iv_usercenter_head:
			
			Intent intent = new Intent(this, FaviconActivity.class);
			startActivity(intent);
			
			break;

		default:
			break;
		}
	}

}
