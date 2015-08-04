package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class StudentInformationActivity extends BaseActivity {

	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_stu_information);

		iv = (ImageView) findViewById(R.id.titlebar_btn_stuback);
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				StudentInformationActivity.this.finish();
			}
		});
	}
}
