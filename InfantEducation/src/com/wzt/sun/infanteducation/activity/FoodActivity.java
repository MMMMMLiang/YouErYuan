package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 这是今日食谱页面
 * @author sun
 *
 */
public class FoodActivity extends BaseActivity {
	private ImageView titlebar_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food);
		
		titlebar_back = (ImageView) findViewById(R.id.titlebar_btn_back);
		
		titlebar_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent mIntent = new Intent(FoodActivity.this, MainActivity.class);
				startActivity(mIntent);
				
			}
		});
	}

}
