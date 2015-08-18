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
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Teacher;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;
import com.wzt.sun.infanteducation.view.CustomerSpinner;

import android.R.integer;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * 评价老师界面
 * @author sun
 *
 */
public class EvaluateActivity extends BaseActivity{

	private ImageView iv;
	
	private CustomerSpinner teaSpinner;
	private CustomerSpinner evaSpinner;
	private static ArrayList<String> tlists;
	private static ArrayList<String> elists;
	private List<Teacher> teachers;
	private ArrayAdapter<String> tAdapter;
	private ArrayAdapter<String> eAdapter;
	private HttpUtils mHttpUtils;
	private ArrayList<String> teaName;
	private String url;
	private String c_id;
	
	private String chooseTeacher;
	private int index;
	private String evaluate;
	private boolean isChoose = false;
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x0013){
				teaSpinner.setList(tlists);
				evaSpinner.setList(elists);
				tAdapter = new ArrayAdapter<String>(EvaluateActivity.this, android.R.layout.simple_spinner_item, tlists);
				teaSpinner.setAdapter(tAdapter);
				eAdapter = new ArrayAdapter<String>(EvaluateActivity.this, android.R.layout.simple_spinner_item, elists);
				evaSpinner.setAdapter(eAdapter);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluate);
		
		initView();
		initStr();
		
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				EvaluateActivity.this.finish();
			}
		});
	}
	
	public void initView() {
		iv = (ImageView) findViewById(R.id.titlebar_eva_btn_back);
		teaSpinner = (CustomerSpinner) findViewById(R.id.evaluate_spinner_teacher);
		evaSpinner = (CustomerSpinner) findViewById(R.id.evaluate_spinner_star);	
		mHttpUtils = new HttpUtils();
		teachers = new ArrayList<Teacher>();
		teaName = new ArrayList<String>();
		c_id = loadC_id()+"";
		
		
		teaSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				chooseTeacher = tlists.get(position).toString();
				index = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		evaSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				evaluate = elists.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	/**
	 * 加载spinner下拉框数据
	 */
	public void initStr(){ 
		url = ConstansUrl.GETTEANAME+c_id;
		mHttpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast("出错");
			}

			@Override
			public void onSuccess(ResponseInfo<String> response) {
				// TODO Auto-generated method stub
				String data = response.result;
				List<Teacher> lists = JsonParseUtils.parseJsonTeacher(data);
				teachers.addAll(lists);
				for (int i = 0; i < teachers.size(); i++) {
					ArrayList<String> teaNames = new ArrayList<String>();
					teaNames.add(teachers.get(i).getT_name());
					teaName.addAll(teaNames);
					
				}
				tlists = new ArrayList<String>();
				tlists.addAll(teaName);
				elists = new ArrayList<String>();
				elists.add("满意");
				elists.add("不满意");
				mHandle.sendEmptyMessage(0x0013);
			}
			
		});
    	
	}
	
	public void btnClick(View view){
		if(view.getId() == R.id.btn_present){
			String str = chooseTeacher;
			if(isChoose){
				BaseApp.getInstance().showToast("您已经提交过了");
			}else{
				RequestParams params = new RequestParams();
				params.addBodyParameter("T_id", teachers.get(index).getT_id()+"");
				params.addBodyParameter("As_content", evaluate);
				mHttpUtils.send(HttpMethod.POST, ConstansUrl.POSTTEANAME, params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast(arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast(response.result);
					}
				});
				if(str.equals(chooseTeacher)){
					isChoose = true;
				}
			}
		}
	}
	
	public int loadC_id(){
		SharedPreferences stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		int cId = stuOrTea.getInt("c_id", 0);
		Log.i("AAAAAAAAAAAAAAAAAA", cId+"");
		return cId;
	}
}
