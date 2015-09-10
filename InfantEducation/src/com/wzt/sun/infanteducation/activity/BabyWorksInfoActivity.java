package com.wzt.sun.infanteducation.activity;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.BabyWork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BabyWorksInfoActivity extends BaseActivity {
	private BabyWork mBabyWork;
	private ImageView iv;
	private ImageView img;
	private TextView tv_title;
	private TextView tv_remark;
	private TextView tv_teacher;
	private TextView tv_date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baby_works_info);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		mBabyWork = (BabyWork) bundle.getSerializable("BABYWORKS");
		
		initView();
		loadData();
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_babyworks_info_btn_back);
		img = (ImageView) findViewById(R.id.bworks_info_iv);
		tv_title = (TextView) findViewById(R.id.bworks_info_title);
		tv_remark = (TextView) findViewById(R.id.bworks_info_remark);
		tv_teacher = (TextView) findViewById(R.id.bworks_info_teacher);
		tv_date = (TextView) findViewById(R.id.bworks_info_date);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BabyWorksInfoActivity.this.finish();
			}
		});
	}
	
	public void loadData(){
		Picasso.with(this).load(mBabyWork.getW_img()).placeholder(R.drawable.message_placeholder_picture).error(R.drawable.message_placeholder_picture).into(img);
		tv_title.setText(mBabyWork.getW_title());
		tv_remark.setText(mBabyWork.getW_remark());
		tv_teacher.setText(mBabyWork.getW_teacher());
		tv_date.setText(mBabyWork.getW_date());
		
	}
}
