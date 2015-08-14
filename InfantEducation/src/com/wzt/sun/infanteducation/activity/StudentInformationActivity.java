package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class StudentInformationActivity extends BaseActivity implements OnClickListener{

	private ImageView iv;
	private ImageView iv_head;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_student_info);

		initView();
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_btn_stuback);
		iv_head = (ImageView) findViewById(R.id.stu_info_low1_image);
		
		iv.setOnClickListener(this);
		iv_head.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.titlebar_btn_stuback:
			StudentInformationActivity.this.finish();
			break;

		case R.id.stu_info_low1_image:
			Intent intent = new Intent(this, FaviconActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
