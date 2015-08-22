package com.wzt.sun.infanteducation.activity;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * 修改密码设置
 * @author Administrator
 *
 */
public class UpdatePasswordActivity extends Activity {
	
	private EditText et_old_psd;
	private EditText et_new_psd;
	private EditText et_new_psd_again;
	private HttpUtils mHttpUtils;
	private String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updata_psd);
		
		initView();
	}
	
	public void initView(){
		et_old_psd = (EditText) findViewById(R.id.et_updata_password_old);
		et_new_psd = (EditText) findViewById(R.id.et_updata_password_new);
		et_new_psd_again = (EditText) findViewById(R.id.et_updata_password_new_again);
		mHttpUtils = new HttpUtils();
		
	}
	
	public void btnClick(View view){
		switch (view.getId()) {
		case R.id.titlebar_updata_psd_btn_back:
			UpdatePasswordActivity.this.finish();
			break;
		case R.id.updata_psd_btn_no:
			UpdatePasswordActivity.this.finish();
			break;
		case R.id.updata_psd_btn_yes:
			String oldPsd = et_old_psd.getText().toString();
			String newPsd = et_new_psd.getText().toString();
			String newPsdAga = et_new_psd_again.getText().toString();
			
			if(TextUtils.isEmpty(newPsdAga) || TextUtils.isEmpty(newPsd) || TextUtils.isEmpty(oldPsd)){
				BaseApp.getInstance().showToast("密码不能为空");
			}else if (!newPsd.equals(newPsdAga)) {
				BaseApp.getInstance().showToast("两次密码输入不一致");
			}else {
				RequestParams params = new RequestParams();
				params.addQueryStringParameter("user", oldPsd);
				params.addQueryStringParameter("pwd", newPsd);
				mHttpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						BaseApp.getInstance().showToast("修改成功");
						Intent mIntent = new Intent(UpdatePasswordActivity.this, LoginActivity.class);
						startActivity(mIntent);
						UpdatePasswordActivity.this.finish();
						MainActivity.instance.finish();
					}
				});
			}
			break;

		default:
			break;
		}
	}

}
