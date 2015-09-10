package com.wzt.sun.infanteducation.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
	private int sId;
	private HttpUtils mHttpUtils;
	private int index;
	private CustomProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		Intent mIntent = getIntent();
		Bundle mBundle = mIntent.getExtras();
		index = mBundle.getInt("BIAOSHI");
		initView();

	}
	
	public void initView(){
		et = (EditText) findViewById(R.id.feed_et_text);
		mHttpUtils = new HttpUtils();
		stuId = loadId();
		sId = loadsId();
		
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
			str = et.getText().toString().trim();
			if(str.equals(null)){
				BaseApp.getInstance().showToast("内容不能为空！");
			}else {
				
				if(index == 1){
					RequestParams params = new RequestParams();
					params.addBodyParameter("r_context", str);
					SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());       
					String date = sDateFormat.format(new Date());
					params.addBodyParameter("r_time", date);
					mHttpUtils.send(HttpMethod.POST, ConstansUrl.POSTTOYUANZHANG, params, new RequestCallBack<String>() {
						
						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							stopProgressDialog();
							BaseApp.getInstance().showToast("上传成功！");
							et.setText("");
						}
					});
				}else {
					RequestParams params = new RequestParams();
					params.addBodyParameter("F_content", str);
					params.addBodyParameter("S_id", sId+"");
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
				}
			}
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
	public int loadsId(){
		SharedPreferences stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		int s_id = stuOrTea.getInt("s_id", 0);
		return s_id;
	}
}
