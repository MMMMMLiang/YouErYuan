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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class PostMessageActivity extends BaseActivity {
	private EditText et_title;
	private EditText et_data;
	private HttpUtils mHttpUtils;
	private String title;
	private String data;
	private int id;
	private int sId;
	private int index;
	private SharedPreferences loginSp = null;
	private SharedPreferences stuOrTea = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_message);
		Intent mIntent = getIntent();
		Bundle mBundle = mIntent.getExtras();
		index = mBundle.getInt("ISPOST");
		initView();
		
	}
	
	public void initView(){
		et_title = (EditText) findViewById(R.id.post_et_title);
		et_data = (EditText) findViewById(R.id.post_et_data);
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		mHttpUtils = new HttpUtils();
		loadData();
	}
	
	public void loadData(){
		id = loginSp.getInt("id", 0);
		sId = loginSp.getInt("num", 0);
	}
	
	public void btnClick(View view){
		switch (view.getId()) {
		case R.id.titlebar_post_btn_back:
			PostMessageActivity.this.finish();
			break;
		case R.id.post_et_btn:
			title = et_title.getText().toString().trim();
			data = et_data.getText().toString().trim();
			if(TextUtils.isEmpty(title) || TextUtils.isEmpty(data)){
				BaseApp.getInstance().showToast("标题、内容不能为空！");
			}else{
				updata();
			}
			
			break;

		default:
			break;
		}
	}
	
	public void updata(){
		RequestParams params = new RequestParams();
		if(index == 1){
			params.addBodyParameter("N_type", 2+"");
			params.addBodyParameter("N_title", title);
			params.addBodyParameter("N_content", data);
			params.addBodyParameter("a_id", id+"");
			params.addBodyParameter("S_id", sId+"");
			params.addBodyParameter("C_id", 0+"");
		}else {
			int cId = stuOrTea.getInt("c_id", 0);
			params.addBodyParameter("N_type", 2+"");
			params.addBodyParameter("N_title", title);
			params.addBodyParameter("N_content", data);
			params.addBodyParameter("a_id", id+"");
			params.addBodyParameter("S_id", sId+"");
			params.addBodyParameter("C_id", cId+"");
		}

		mHttpUtils.send(HttpMethod.POST, ConstansUrl.POSTINFORM, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				//BaseApp.getInstance().showToast("发送失败！");
				BaseApp.getInstance().showToast(arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast("发送成功！");
				PostMessageActivity.this.finish();
			}
		});
	}
}
