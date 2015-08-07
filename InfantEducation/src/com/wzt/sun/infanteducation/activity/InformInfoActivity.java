package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Inform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class InformInfoActivity extends BaseActivity {
	
	private Inform mInform;
	private ImageView iv;
	private TextView title;
	private TextView date;
	private TextView info;
	private TextView author;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inform_info);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		// 接收Serializable对象
		mInform = (Inform) bundle.getSerializable("InformInfo1");

		initView();
		loadData();
		
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				InformInfoActivity.this.finish();
			}
		});
		
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_inform_info_back);
		title = (TextView) findViewById(R.id.inform_info_title);
		date = (TextView) findViewById(R.id.inform_info_date);
		info = (TextView) findViewById(R.id.inform_info_info);
		author = (TextView) findViewById(R.id.inform_info_author);
	}
	
	public void loadData(){
		title.setText(mInform.getN_title());
		date.setText(mInform.getN_date());
		info.setText(mInform.getN_content());
		if(mInform.getN_author() == 1){
			author.setText("教务处");
		}
	}

}
