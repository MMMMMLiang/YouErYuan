package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.view.CustomProgressDialog;
import com.wzt.sun.infanteducation.view.CustomerSpinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

public class LeaveActivity extends Activity {
	
	private ImageView iv;
	private EditText et_time;
	private EditText et_text;
	
	private String str_time = null;
	private String str_text = null;
	private String str_type;
	
	private HttpUtils mHttpUtils;
	
	private CustomerSpinner mSpinner;
	private static ArrayList<String> lists;
	private ArrayAdapter<String> adapter;
	
	private CustomProgressDialog progressDialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_leave);
		initStr();
		initView();
	}
	
	public void initView() {
		iv = (ImageView) findViewById(R.id.titlebar_leave_btn_back);
		mSpinner = (CustomerSpinner) findViewById(R.id.leave_spinner);
		et_time = (EditText) findViewById(R.id.leave_et_time);
		et_text = (EditText) findViewById(R.id.leave_et_data);
		mSpinner.setList(lists);
		mHttpUtils = new HttpUtils();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lists);
		mSpinner.setAdapter(adapter);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LeaveActivity.this.finish();
			}
		});
		//下拉框监听
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				str_type = lists.get(position).toString();
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
    	lists.add("事假");
    	lists.add("病假");
    	lists.add("其他");
	}
	
	public void btnClick(View view){
		switch (view.getId()) {
		case R.id.leave_btn:
			startProgressDialog();
			RequestParams params = new RequestParams("GBK");
			//HttpEntity bodyEntity = new UrlEncodedFormEntity(str_time,HTTP.UTF_8);
			str_time = et_time.getText().toString();
			str_text = et_text.getText().toString();
			
			params.addQueryStringParameter("L_type", str_type);
			params.addQueryStringParameter("L_day", str_time);
			params.addQueryStringParameter("L_cause", str_text);
			mHttpUtils.send(HttpMethod.POST, ConstansUrl.POSTLEAVE, params, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					BaseApp.getInstance().showToast(arg1.toString());
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					stopProgressDialog();
					String result = arg0.result;
					BaseApp.getInstance().showToast(result);
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

}
