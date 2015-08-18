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
import com.wzt.sun.infanteducation.bean.Attendance;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

public class LogBookActivity extends BaseActivity {
	private ListView lb_listView;
	private List<Attendance> lists;
	private CommonAdapter<Attendance> adapter;
	private HttpUtils mHttpUtils;
	
	private String url;
	private int stu_id;
	
	//管理线程,保证始终只开一个线程
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x0011){
				adapter.notifyDataSetChanged();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_logbook);
		
		initView();
		
	}
	
	public void initView(){
		lb_listView = (ListView) findViewById(R.id.logbook_listview);
		lists = new ArrayList<Attendance>();
		mHttpUtils = new HttpUtils();
		stu_id = loadId();
		loadData();
		
		adapter = new CommonAdapter<Attendance>(LogBookActivity.this, R.layout.activity_logbook_item, lists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, Attendance item) {
				viewHolder.setTextForTextView(R.id.logbook_item_date, item.getA_date());
				viewHolder.setTextForTextView(R.id.logbook_item_am, item.getA_am());
				viewHolder.setTextForTextView(R.id.logbook_item_pm, item.getA_pm());
				viewHolder.setTextForTextView(R.id.logbook_item_bz, item.getA_type());
				
			}
		};
		lb_listView.setAdapter(adapter);
	}
	
	public void loadData(){
		url = ConstansUrl.GETSTUATTEND+stu_id;
		executor.execute(new Runnable() {

			@Override
			public void run() {
				mHttpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						String data = response.result;
						List<Attendance> attendances = JsonParseUtils.parseJsonAttendance(data);
						lists.addAll(attendances);
						mHandle.sendEmptyMessage(0x0011);
					}
				});
			}
			
		});
	}
	
	public int loadId(){
		SharedPreferences stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		int id = stuOrTea.getInt("id", 0);
		return id;
	}

}
