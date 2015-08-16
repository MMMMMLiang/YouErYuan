package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.User;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private EditText et_login_username;
	private EditText et_login_password;
	
	private static ArrayList<String> lists;
	
	private HttpUtils mHttpUtils;
	private String log;
	
	private SharedPreferences loginSp = null;
	private SharedPreferences userInfo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}
	
	public void initView() {
		et_login_username = (EditText) findViewById(R.id.et_login_username);
		et_login_password = (EditText) findViewById(R.id.et_login_password);
		mHttpUtils = new HttpUtils();
	}
	
	public void btnClick(View view){
		switch (view.getId()) {
		case R.id.btn_login_login:
			//获取用户名、密码
			String userName = et_login_username.getText().toString();
			String passWord = et_login_password.getText().toString();
			Log.i("TEST", userName+","+passWord);
			if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)){
				BaseApp.getInstance().showToast("用户名、密码不能为空！");
			}else {
				RequestParams params = new RequestParams();
				params.addQueryStringParameter("user", userName);
				params.addQueryStringParameter("pwd", passWord);
				mHttpUtils.send(HttpMethod.POST, ConstansUrl.loginUrl(), params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast(arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						String str = responseInfo.result;
						saveUserInfo(str);
						BaseApp.getInstance().showToast("success");
						//保存登录状态
						loadLogin();
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					}
				});
			}
			break;

		default:
			break;
		}
	}
	
	/**
	 * 保存登录状态
	 */
	private void loadLogin() {
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		Editor editor=loginSp.edit();
		editor.putBoolean("isLogin", true);
		editor.commit();
	}
	
	/**
	 * 保存用户信息
	 */
	private void saveUserInfo(String str){
		List<User> users = JsonParseUtils.parseJsonUser(str);
		userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		Editor editor=userInfo.edit();
		
		editor.putInt("vid", users.get(0).getVid());
		editor.putInt("jifen", users.get(0).getJifen());
		log = users.get(0).getVsf();
		editor.putString("vsf", log);
		editor.putString("vsfname", users.get(0).getVsfname());
		editor.putString("user", users.get(0).getUser());
		editor.putString("password", users.get(0).getPassword());
		editor.putString("name", users.get(0).getName());
		editor.putString("phone", users.get(0).getPhone());
		editor.putString("registerdate", users.get(0).getRegisterdate());
		editor.putString("state", users.get(0).getState());
		editor.putString("identity", users.get(0).getIdentity());
		editor.putString("email", users.get(0).getEmail());
		editor.putString("address", users.get(0).getAddress());
		editor.putString("appointmenttime", users.get(0).getAppointmenttime());
		editor.putString("remarks1", users.get(0).getRemarks1());
		editor.putString("remarks2", users.get(0).getRemarks2());
		if(log.equals("A")){
			// 是家长
			editor.putBoolean("isParent", true);
			editor.putBoolean("isTeacher", false);
		}else if (log.equals("B")) {
			// 是老师
			editor.putBoolean("isParent", false);
			editor.putBoolean("isTeacher", true);
		}
		
		editor.commit();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			lists.clear();
			//点击返回键跳转到主界面
			Intent intent=new Intent(this,MainActivity.class);
			startActivity(intent);
		}
		return super.onKeyDown(keyCode, event);
	}

}
