package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.ACache;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * user用户个人信息显示
 * @author sun.ml
 *
 */
public class PersonalInfoActivity extends BaseActivity {
	
	private ImageView btn_goBack;
	private ImageView img_head;
	private TextView text_name;
	private TextView text_sex;
	private TextView text_phone;
	private SharedPreferences loginSp = null;
	private ACache mACache;
	
	private Handler mHamdle = new Handler(){
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 0x0006:
				Bitmap bm = mACache.getAsBitmap("iconBitmap");
				img_head.setImageBitmap(bm); 
				break;

			default:
				break;
			}
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		
		btn_goBack = (ImageView) findViewById(R.id.titlebar_personal_info_btnback);
		img_head = (ImageView) findViewById(R.id.iv_usercenter_head);
		
		initView();
		loadData();
		btn_goBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				PersonalInfoActivity.this.finish();
				
			}
		});
	}
	
	public void initView() {
		
		text_name = (TextView) findViewById(R.id.personal_t_name);
		text_sex = (TextView) findViewById(R.id.personal_t_sex);
		text_phone = (TextView) findViewById(R.id.personal_t_phone);
	}
	
	public void loadData() {
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		String name = loginSp.getString("name", null);
		String email = loginSp.getString("email", null);
		String phone = loginSp.getString("phone", null);
		text_name.setText("姓        名："+name);
		text_sex.setText("邮        箱："+email);
		text_phone.setText("联系方式："+phone);
		mACache = ACache.get(this);
		new Thread(){
			public void run() {
				
				mHamdle.sendEmptyMessage(0x0006);
			};
		}.start();
		
	}
	
	public void ivClick(View view){
		switch (view.getId()) {
		case R.id.iv_usercenter_head:
			
			Intent intent = new Intent(this, FaviconActivity.class);
			startActivity(intent);
			
			break;

		default:
			break;
		}
	}

}
