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
import com.wzt.sun.infanteducation.view.CustomProgressDialog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * 反馈院长页面
 * 
 * @author sun
 * 
 */
public class FeedbackActivity extends BaseActivity {
	private EditText et;
	private String str;
	private int stuId;
	private HttpUtils mHttpUtils;
	private CustomProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		
		initView();

	}
	
	public void initView(){
		et = (EditText) findViewById(R.id.feed_et_text);
		mHttpUtils = new HttpUtils();
		stuId = loadId();
	}

	public void btnClick(View view) {
		switch (view.getId()) {
		// 点击返回箭头
		case R.id.titlebar_feet_btn_back:
			FeedbackActivity.this.finish();
			break;
		// 点击确定
		case R.id.feed_bar_btn:
			startProgressDialog();
			str = et.getText().toString();
			RequestParams params = new RequestParams();
			params.addBodyParameter("F_content", str);
			params.addBodyParameter("F_authr", stuId+"");
			mHttpUtils.send(HttpMethod.POST, ConstansUrl.POSTYIJIAN, params, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					stopProgressDialog();
					BaseApp.getInstance().showToast("上传成功！");
				}
			});
			break;

		default:
			break;
		}
	}
	
	private void startProgressDialog(){
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("正在上传...");
        }
         
        progressDialog.show();
    }
	
	private void stopProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
	
	public int loadId(){
		SharedPreferences stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		int id = stuOrTea.getInt("id", 0);
		return id;
	}
}
