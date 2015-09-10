package com.wzt.sun.infanteducation.activity;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.News;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SchoolNewsInfoActivity extends BaseActivity {
	private News mNews;
	private TextView tv_title;
	private TextView tv_title2;
	private TextView tv_content;
	private TextView tv_date;
	private ImageView iv_head;
	private ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school_news_info);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		mNews = (News) bundle.getSerializable("NEWS");
		initView();
		loadData();
	}
	
	public void initView(){
		tv_title = (TextView) findViewById(R.id.school_news_info_title);
		tv_title2 = (TextView) findViewById(R.id.school_news_info_2title);
		tv_content = (TextView) findViewById(R.id.school_news_info_content);
		tv_date = (TextView) findViewById(R.id.school_news_info_date);
		iv = (ImageView) findViewById(R.id.school_news_info_iv);
		iv_head = (ImageView) findViewById(R.id.titlebar_newsinfo_btn_back);
		iv_head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SchoolNewsInfoActivity.this.finish();
			}
		});
	}
	
	public void loadData(){
		tv_title.setText(mNews.getN_title());
		if (mNews.getN_ctitle() == null) {
			tv_title2.setVisibility(View.GONE);
		}else {
			tv_title2.setText(mNews.getN_ctitle());
		}
		tv_content.setText(mNews.getN_content());
		tv_date.setText(mNews.getN_date());
		Picasso.with(this).load(mNews.getN_img()).placeholder(R.drawable.message_placeholder_picture).error(R.drawable.message_placeholder_picture).into(iv);
	}
}
