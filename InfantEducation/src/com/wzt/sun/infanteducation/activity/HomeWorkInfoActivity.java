package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 课时作业
 * @author sun.ml
 *
 */
public class HomeWorkInfoActivity extends BaseActivity {
	
	private ImageView iv;
	private TextView title;
	private TextView date;
	private TextView info;
	private TextView author;
	private Homework mHomework;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homework_info);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		mHomework = (Homework) bundle.getSerializable("HomeWork1");
		
		initView();
		loadData();
		
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				HomeWorkInfoActivity.this.finish();
			}
		});
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_home_info_back);
		title = (TextView) findViewById(R.id.home_work_title);
		date = (TextView) findViewById(R.id.home_work_date);
		info = (TextView) findViewById(R.id.home_work_info);
		author = (TextView) findViewById(R.id.home_work_author);
	}
	
	public void loadData(){
		title.setText(mHomework.getH_title());
		date.setText(mHomework.getH_date());
		info.setText(mHomework.getH_content());		
		author.setText(mHomework.getH_authr()+"");
		
	}
}
