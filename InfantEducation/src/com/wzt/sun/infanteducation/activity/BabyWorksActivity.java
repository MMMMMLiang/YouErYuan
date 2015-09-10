package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.wzt.sun.infanteducation.bean.BabyWork;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class BabyWorksActivity extends BaseActivity {
	private GridView mGridView;
	private CommonAdapter<BabyWork> adapter;
	private List<BabyWork> lists;
	private HttpUtils mHttpUtils;
	private ImageView iv;
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0026) {
				adapter.notifyDataSetChanged();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baby_works);
		
		initView();
	}
	
	public void initView(){
		mGridView = (GridView) findViewById(R.id.baby_works_gridview);
		iv = (ImageView) findViewById(R.id.titlebar_babyworks_btn_back);
		lists = new ArrayList<BabyWork>();
		mHttpUtils = new HttpUtils();
		loadData();
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				BabyWorksActivity.this.finish();
			}
		});
		adapter = new CommonAdapter<BabyWork>(BabyWorksActivity.this, R.layout.activity_baby_works_item, lists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, BabyWork item) {
				// TODO Auto-generated method stub
				viewHolder.setTextForTextView(R.id.bworks_item_title, item.getW_title());
				viewHolder.setImageForGridView(BabyWorksActivity.this, R.id.bworks_item_iv, item.getW_img());
			}
		};
		mGridView.setAdapter(adapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				BabyWork mBabyWork = lists.get(position);
				Intent intent = new Intent(BabyWorksActivity.this,BabyWorksInfoActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("BABYWORKS", mBabyWork);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
	}
	
	public void loadData(){
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETBABYWORKS, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast(arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						String data = arg0.result;
						List<BabyWork> mBabyWorks = JsonParseUtils.parseJsonBabyWorks(data);
						lists.clear();
						lists.addAll(mBabyWorks);
						mHandle.sendEmptyMessage(0x0026);
						
					}
					
				});
				
			}
		});
	}
}
