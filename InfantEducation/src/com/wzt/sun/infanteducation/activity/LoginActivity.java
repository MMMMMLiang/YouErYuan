package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.view.CustomerSpinner;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private EditText et_login_username;
	private EditText et_login_password;
	private CustomerSpinner mSpinner;
	
	private static ArrayList<String> lists;
	private ArrayAdapter<String> adapter;
	
	private String str;
	private HttpUtils mHttpUtils;
	
	private SharedPreferences loginSp = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initStr();
		initView();
	}
	
	public void initView() {
		et_login_username = (EditText) findViewById(R.id.et_login_username);
		et_login_password = (EditText) findViewById(R.id.et_login_password);
		mSpinner = (CustomerSpinner) findViewById(R.id.login_spinner);
		mSpinner.setList(lists);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lists);
		mSpinner.setAdapter(adapter);
		mHttpUtils = new HttpUtils();
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				str = lists.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}
	/**
	 * 加载spinner下拉框数据
	 */
	public void initStr(){
		lists = new ArrayList<String>();
    	lists.add("教师");
    	lists.add("家长");
    	lists.add("园长");
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
			}else if (str.equals("")) {
				BaseApp.getInstance().showToast("请选择身份！");
			}else {
				RequestParams params = new RequestParams();
				params.addQueryStringParameter("user", userName);
				params.addQueryStringParameter("pwd", passWord);
				mHttpUtils.send(HttpMethod.POST, ConstansUrl.loginUrl(), params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast("fail");
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
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
