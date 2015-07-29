package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.lidroid.xutils.HttpUtils;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.view.CustomerSpinner;

public class LoginActivity extends BaseActivity {
	private EditText et_login_username;
	private EditText et_login_password;
	private CustomerSpinner mSpinner;
	
	private static ArrayList<String> lists;
	private ArrayAdapter<String> adapter;
	
	private String str;
	private HttpUtils mHttpUtils;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
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
	public void init(){
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
			
			if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)){
				BaseApp.getInstance().showToast("用户名、密码不能为空！");
			}else if (str.equals("")) {
				BaseApp.getInstance().showToast("请选择身份！");
			}
			break;

		default:
			break;
		}
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
