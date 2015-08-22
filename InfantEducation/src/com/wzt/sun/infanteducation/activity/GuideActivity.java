package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 引导界面
 * @author sun.ml
 *
 */
public class GuideActivity extends BaseActivity {
	private Handler mHandle = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			if(msg.what==0x001){
				Intent intent=new Intent(GuideActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		new Thread(){
			@Override
			public void run() {
				try {
					sleep(1000);
					mHandle.sendEmptyMessage(0x001);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
}
