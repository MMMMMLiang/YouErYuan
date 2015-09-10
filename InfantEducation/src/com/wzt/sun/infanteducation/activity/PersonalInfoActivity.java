package com.wzt.sun.infanteducation.activity;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Teacher;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.CircleTransform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
	private TextView text_lv;
	private TextView text_date;
	private TextView text_volk;
	private TextView text_job;
	private TextView text_phone;
	private TextView text_address;
	private String url;
	private String spfUrl;
	private int index;
	private SharedPreferences loginSp = null;
	private SharedPreferences stuOrTea = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);

		btn_goBack = (ImageView) findViewById(R.id.titlebar_personal_info_btnback);
		img_head = (ImageView) findViewById(R.id.iv_usercenter_head);
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		spfUrl = stuOrTea.getString("photo", null);
		Intent mIntent = getIntent();
		Bundle mBundle = mIntent.getExtras();
		if(mIntent != null){
			url = mIntent.getStringExtra("headUrl");
			index = mBundle.getInt("ISTEA");
		}
		initTeaView();
		loadTeaData();
		btn_goBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				PersonalInfoActivity.this.finish();

			}
		});
	}
	
	public void initTeaView() {
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		text_name = (TextView) findViewById(R.id.people_t_name);
		text_sex = (TextView) findViewById(R.id.people_t_sex);
		text_lv = (TextView) findViewById(R.id.people_t_lv);
		text_date = (TextView) findViewById(R.id.people_t_date);
		text_volk = (TextView) findViewById(R.id.people_t_volk);
		text_job = (TextView) findViewById(R.id.people_t_job);
		text_phone = (TextView) findViewById(R.id.people_t_phone);
		text_address = (TextView) findViewById(R.id.people_t_address);
	}
	
	public void loadTeaData() {
		String name = null, sex = null, lv = null, date = null, volk = null, job = null, phone = null, address = null;
		if(index == 1){
			name = loginSp.getString("t_name", null);
			sex = loginSp.getString("t_address", null);
			lv = loginSp.getString("t_lv", null);
			date = loginSp.getString("t_date", null);
			volk = loginSp.getString("t_volk", null);
			job = loginSp.getString("t_job", null);
			phone = loginSp.getString("t_phone", null);
			address = loginSp.getString("t_address", null);
			Picasso.with(this).load(ConstansUrl.getHeadnUrl(spfUrl)).placeholder(R.drawable.avatar).error(R.drawable.avatar).transform(new CircleTransform()).into(img_head);
		}else {
			Intent intent = getIntent();
	        Bundle bundle = intent.getExtras();
	        // 接收Serializable对象
	        Teacher mTeacher = (Teacher) bundle.getSerializable("ISLEA");
	        name = mTeacher.getT_name();
			sex = mTeacher.getT_sex();
			lv = mTeacher.getT_lv();
			date = mTeacher.getT_date();
			volk = mTeacher.getT_volk();
			job = mTeacher.getT_job();
			phone = mTeacher.getT_phone();
			address = mTeacher.getT_address();
			Picasso.with(this).load(ConstansUrl.getHeadnUrl(mTeacher.getT_img())).placeholder(R.drawable.avatar).error(R.drawable.avatar).transform(new CircleTransform()).into(img_head);
		}
		text_name.setText(name);
		text_sex.setText(sex);
		text_lv.setText(lv);
		text_date.setText(date);
		text_volk.setText(volk);
		text_job.setText(job);
		text_phone.setText(phone);
		text_address.setText(address);
		
		
	}
	
	public void ivClick(View view){
		switch (view.getId()) {
		case R.id.iv_usercenter_head:
			
			Intent intent = new Intent(this, FaviconActivity.class);
			intent.putExtra("headUrl", url);
			startActivityForResult(intent, 0);
			
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 0 && resultCode == 1){
			Bundle bundle = data.getExtras();
	        String imgUrl = bundle.getString("imgUrl");
	        Picasso.with(this).load(ConstansUrl.getHeadnUrl(imgUrl)).placeholder(R.drawable.avatar).error(R.drawable.avatar).transform(new CircleTransform()).into(img_head);
		}
	}
}
