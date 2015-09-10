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
 * 学生设置界面
 * @author sun.ml
 *
 */
public class SettingStuActivity extends BaseActivity {
	
	private EditText text_volk;
	private EditText text_birthday;
	private EditText text_hea;
	private EditText text_fphone;
	private EditText text_mphone;
	private EditText text_address;
	private SharedPreferences loginSp = null;
	private SharedPreferences userInfo = null;
	
	private HttpUtils mHttpUtils;
	private ImageView iv;
	
	private String birthday;
	private String hea;
	private String volk;
	private String fphone;
	private String mphone;
	private String address;
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_edu);
		
		initView();
		loadData();
	}
	
	public void initView(){
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		iv = (ImageView) findViewById(R.id.titlebar_setting_stu_btn_back);
		text_volk = (EditText) findViewById(R.id.people_stu_volk);
		text_birthday = (EditText) findViewById(R.id.people_stu_birthday);
		text_hea = (EditText) findViewById(R.id.people_stu_hea);
		text_fphone = (EditText) findViewById(R.id.people_stu_fphone);
		text_mphone = (EditText) findViewById(R.id.people_stu_mphone);
		text_address = (EditText) findViewById(R.id.people_stu_address);
		mHttpUtils = new HttpUtils();
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SettingStuActivity.this.finish();
			}
		});
	}
	
	public void loadData(){
		id = userInfo.getInt("num", 0);
		volk = loginSp.getString("st_volk", null);
		birthday = loginSp.getString("st_birthday", null);
		hea = loginSp.getString("st_health", null);
		fphone = loginSp.getString("st_fcard", null);
		mphone = loginSp.getString("st_mcard", null);
		address = loginSp.getString("st_address", null);
		text_volk.setText(volk);
		text_birthday.setText(birthday);
		text_hea.setText(hea);
		text_fphone.setText(fphone);
		text_mphone.setText(mphone);
		text_address.setText(address);
		
	}
	
	public void ivClick(View view){
		if (view.getId() == R.id.titlebar_setting_stu_btn_sure) {
			if(isEmpty()){
				volk = text_volk.getText().toString();
				birthday = text_birthday.getText().toString();
				hea = text_hea.getText().toString();
				fphone = text_fphone.getText().toString();
				mphone = text_mphone.getText().toString();
				address = text_address.getText().toString();
				
				RequestParams params = new RequestParams();
				params.addBodyParameter("St_id", id+"");
				params.addBodyParameter("St_volk", volk);
				params.addBodyParameter("St_birthday", birthday);
				params.addBodyParameter("St_health", hea);
				params.addBodyParameter("St_fcard", fphone);
				params.addBodyParameter("St_mcard", mphone);
				params.addBodyParameter("St_address", address);
				mHttpUtils.send(HttpMethod.POST, ConstansUrl.POSTSTUINFO, params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast(arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast("修改成功！");
						save();
						SettingStuActivity.this.finish();
					}
				});
			}else {
				BaseApp.getInstance().showToast("个人信息不能为空！");
			}
		}else if (view.getId() == R.id.titlebar_setting_stu_btn_back) {
			SettingStuActivity.this.finish();
		}
	}
	
	private void save(){
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		Editor mEditor = loginSp.edit();
		
		mEditor.putString("st_volk", volk);
		mEditor.putString("st_birthday", birthday);
		mEditor.putString("st_health", hea);
		mEditor.putString("st_fcard", fphone);
		mEditor.putString("st_mcard", mphone);
		mEditor.putString("st_address", address);
		mEditor.commit();
	}
	
	private boolean isEmpty(){
		if(TextUtils.isEmpty(volk) || TextUtils.isEmpty(birthday) || TextUtils.isEmpty(hea) || TextUtils.isEmpty(fphone) || TextUtils.isEmpty(mphone) || TextUtils.isEmpty(address)){
			return false;
		}else {
			return true;
		}
	}
}
