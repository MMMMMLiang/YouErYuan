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
import com.wzt.sun.infanteducation.adapter.CommonAdapter;
import com.wzt.sun.infanteducation.adapter.CommonViewHolder;
import com.wzt.sun.infanteducation.bean.Classes;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class AllPlanActivity extends BaseActivity {
	private ImageView iv;
	private ListView mListView;
	private List<Classes> lists;
	private HttpUtils mHttpUtils;
	private CommonAdapter<Classes> adapter;
	private SharedPreferences userInfo = null;
	private int sId;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x0032){
				adapter.notifyDataSetChanged();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_plan);
		
		initView();
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_allplan_btn_back);
		mListView = (ListView) findViewById(R.id.all_plan_listview);
		userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		sId = userInfo.getInt("num", 0);
		lists = new ArrayList<Classes>();
		mHttpUtils = new HttpUtils();
		loadData();
		
		adapter = new CommonAdapter<Classes>(AllPlanActivity.this, R.layout.activity_all_plan_item, lists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, Classes item) {
				// TODO Auto-generated method stub
				viewHolder.setTextForTextView(R.id.all_plan_item_tv, item.getC_name());
			}
		};
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				int id = lists.get(position).getC_id();
				Intent mIntent = new Intent(AllPlanActivity.this , CourseActivity.class);
				mIntent.putExtra("ALLCOURSE", 1);
				mIntent.putExtra("CID", id);
				startActivity(mIntent);
			}
		});
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AllPlanActivity.this.finish();
			}
		});
		
	}
	
	public void loadData(){
		RequestParams params = new RequestParams();
		params.addBodyParameter("S_id", sId+"");
		mHttpUtils.send(HttpMethod.POST, ConstansUrl.GETALLCLS, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast("连接失败，请稍候再试！");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String data = arg0.result;
				List<Classes> classes = JsonParseUtils.parseJsonClasses(data);
				lists.clear();
				lists.addAll(classes);
				handler.sendEmptyMessage(0x0032);
			}
		});
	}
	
}
