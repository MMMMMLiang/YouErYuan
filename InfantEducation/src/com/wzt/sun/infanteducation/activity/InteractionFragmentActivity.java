package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 通知通告列表(fragment)
 * @author sun.ml
 *
 */
public class InteractionFragmentActivity extends BaseActivity {
	
	private ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interaction_info);
		
		iv = (ImageView) findViewById(R.id.titlebar_interaction_btn_back);
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				InteractionFragmentActivity.this.finish();
			}
		});
	}

}
