package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.adapter.CommonAdapter;
import com.wzt.sun.infanteducation.adapter.CommonViewHolder;
import com.wzt.sun.infanteducation.bean.Classes;
import com.wzt.sun.infanteducation.bean.Student;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;
import com.wzt.sun.infanteducation.view.CustomerSpinner;
import com.wzt.sun.infanteducation.view.MyListView;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.ListView;

public class AllStuInformationActivity extends BaseActivity {

	private ListView mListView;
	private ImageView iv;
	private List<Student> lists;
	private HttpUtils mHttpUtils;
	private CommonAdapter<Student> adapter;

	private CustomerSpinner clsSpinner;
	private static ArrayList<String> clists;
	private ArrayList<String> clsName;
	private List<Classes> cLists;
	private ArrayAdapter<String> cAdapter;
	private LinearLayout ll_choose;

	private SharedPreferences loginSp = null;
	private boolean isLea = false;

	private int c_id;
	private int index;
	private String url;

	private Handler mHandle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x0017){
				clsSpinner.setList(clists);
				cAdapter = new ArrayAdapter<String>(AllStuInformationActivity.this, android.R.layout.simple_spinner_item, clists);
				clsSpinner.setAdapter(cAdapter);
			}else if (msg.what == 0x0015) {
				adapter.notifyDataSetChanged();
			}else if (msg.what == 0x0019) {
				c_id = cLists.get(index).getC_id();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_stu_info);

		initView();
		initStr();
		/*new Thread(){
			public void run() {
				loadData();
			};
		}.start();*/
	}

	public void initView() {
		mListView = (ListView) findViewById(R.id.all_stu_listview);
		iv = (ImageView) findViewById(R.id.titlebar_allstu_btn_back);
		ll_choose = (LinearLayout) findViewById(R.id.all_stu_choose_cls);
		clsSpinner = (CustomerSpinner) findViewById(R.id.evaluate_spinner_class);
		lists = new ArrayList<Student>();
		clists = new ArrayList<String>();
		cLists = new ArrayList<Classes>();
		clsName = new ArrayList<String>();
		mHttpUtils = new HttpUtils();
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		isLea = loginSp.getBoolean("isLeader", false);
		choose();
		
		adapter = new CommonAdapter<Student>(this, R.layout.activity_all_stu_infi_item, lists) {

			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, Student item) {
				viewHolder.setImageForFoodView(AllStuInformationActivity.this, R.id.allstu_item_icon,
						item.getSt_photo());
				viewHolder.setTextForTextView(R.id.allstu_item_name, item.getSt_name());
				viewHolder.setTextForTextView(R.id.allstu_item_sex, item.getSt_sex());
				viewHolder.setTextForTextView(R.id.allstu_item_id, item.getSt_id() + "");
			}
		};
		mListView.setAdapter(adapter);
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AllStuInformationActivity.this.finish();
			}
		});
		
		clsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				index = position;
				c_id = cLists.get(index).getC_id();
				loadData();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void choose(){
		if(!isLea){
			ll_choose.setVisibility(View.GONE);
			loadData();
		}
	}

	private void initStr() {
		mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETALLCLS, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast(arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> response) {
				// TODO Auto-generated method stub
				String data = response.result;
				List<Classes> clss = JsonParseUtils.parseJsonClasses(data);
				cLists.addAll(clss);
				for (int i = 0; i < cLists.size(); i++) {
					ArrayList<String> clsNames = new ArrayList<String>();
					clsNames.add(cLists.get(i).getC_name());
					clsName.addAll(clsNames);
				}
				clists.addAll(clsName);
				mHandle.sendEmptyMessage(0x0017);
			}
		});
	}

	public void loadData() {
		if(isLea){
			mHandle.sendEmptyMessage(0x0019);
			//c_id = cLists.get(index).getC_id();
		}else {
			c_id = loadC_id();
		}
		url = ConstansUrl.GETALLSTU + c_id;
		mHttpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast(arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> response) {
				// TODO Auto-generated method stub
				String data = response.result;
				List<Student> stus = JsonParseUtils.parseJsonStudents(data);
				lists.addAll(stus);
				mHandle.sendEmptyMessage(0x0015);
			}
		});
	}

	public int loadC_id() {
		SharedPreferences stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		int cId = stuOrTea.getInt("c_id", 0);
		return cId;
	}
}
