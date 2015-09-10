package com.wzt.sun.infanteducation.activity;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * 教师个人信息设置
 * @author sun.ml
 *
 */
public class SettingTeaActivity extends BaseActivity {
	private EditText text_name;
	private EditText text_sex;
	private EditText text_lv;
	private EditText text_date;
	private EditText text_volk;
	private EditText text_job;
	private EditText text_phone;
	private EditText text_address;
	private SharedPreferences loginSp = null;
	private SharedPreferences userInfo = null;
	
	private HttpUtils mHttpUtils;
	private ImageView iv;
	
	private String name;
	private String sex;
	private String lv;
	private String date;
	private String volk;
	private String job;
	private String phone;
	private String address;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_tea);
		
		initView();
		loadData();
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SettingTeaActivity.this.finish();
			}
		});
	}
	
	public void initView(){
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		iv = (ImageView) findViewById(R.id.titlebar_stea_btn_back);
		text_name = (EditText) findViewById(R.id.people_st_name);
		text_sex = (EditText) findViewById(R.id.people_st_sex);
		text_lv = (EditText) findViewById(R.id.people_st_lv);
		text_date = (EditText) findViewById(R.id.people_st_date);
		text_volk = (EditText) findViewById(R.id.people_st_volk);
		text_job = (EditText) findViewById(R.id.people_st_job);
		text_phone = (EditText) findViewById(R.id.people_st_phone);
		text_address = (EditText) findViewById(R.id.people_st_address);
		mHttpUtils = new HttpUtils();
	}
	
	public void loadData(){
		id = userInfo.getInt("num", 0);
		name = loginSp.getString("t_name", null);
		sex = loginSp.getString("t_address", null);
		lv = loginSp.getString("t_lv", null);
		date = loginSp.getString("t_date", null);
		volk = loginSp.getString("t_volk", null);
		job = loginSp.getString("t_job", null);
		phone = loginSp.getString("t_phone", null);
		address = loginSp.getString("t_address", null);
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
		if (view.getId() == R.id.titlebar_setting_tea_btn_sure) {
			if(isEmpty()){
				name = text_name.getText().toString();
				sex = text_sex.getText().toString();
				lv = text_lv.getText().toString();
				date = text_date.getText().toString();
				volk = text_volk.getText().toString();
				job = text_job.getText().toString();
				phone = text_phone.getText().toString();
				address = text_address.getText().toString();
				
				RequestParams params = new RequestParams();
				params.addBodyParameter("T_id", id+"");
				params.addBodyParameter("T_name", name);
				params.addBodyParameter("T_sex", sex);
				params.addBodyParameter("T_lv", lv);
				params.addBodyParameter("T_date", date);
				params.addBodyParameter("T_volk", volk);
				params.addBodyParameter("T_job", job);
				params.addBodyParameter("T_phone", phone);
				params.addBodyParameter("T_address", address);
				mHttpUtils.send(HttpMethod.POST, ConstansUrl.POSTTEAINFO, params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast("修改失败");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast("修改成功！");
						save();
						SettingTeaActivity.this.finish();
					}
				});
			}else {
				BaseApp.getInstance().showToast("个人信息不能为空！");
			}
		}
	}
	
	private void save(){
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		Editor mEditor = loginSp.edit();
		
		mEditor.putString("t_name", name);
		mEditor.putString("t_address", sex);
		mEditor.putString("t_lv", lv);
		mEditor.putString("t_date", date);
		mEditor.putString("t_volk", volk);
		mEditor.putString("t_job", job);
		mEditor.putString("t_phone", phone);
		mEditor.putString("t_address", address);
		mEditor.commit();
	}
	
	private boolean isEmpty(){
		if(TextUtils.isEmpty(name) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(lv) || TextUtils.isEmpty(date) || TextUtils.isEmpty(volk) || TextUtils.isEmpty(job) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)){
			return false;
		}else {
			return true;
		}
	}
}
