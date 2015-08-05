package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 明星宝宝页面
 * @author sun
 *
 */
public class StarActivity extends BaseActivity {
	
	private ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star);
		
		iv = (ImageView) findViewById(R.id.titlebar_star_btn_back);
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				StarActivity.this.finish();
			}
		});
	}

}
