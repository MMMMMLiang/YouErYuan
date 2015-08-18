package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.adapter.CommonAdapter;
import com.wzt.sun.infanteducation.adapter.CommonViewHolder;
import com.wzt.sun.infanteducation.bean.Homework;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 教学计划页面
 * @author sun
 *
 */
public class PlanActivity extends BaseActivity implements OnRefreshListener2<ListView>{
	
	private ImageView iv;
	private PullToRefreshListView ptrListView;
	private List<Homework> lists;
	private CommonAdapter<Homework> adapter;
	private HttpUtils mHttpUtils;
	
	private int classId;
	private String url;
	//管理线程,保证始终只开一个线程
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0010) {
				ptrListView.onRefreshComplete();
				adapter.notifyDataSetChanged();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan);
		
		initView();
		loadData();
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_plant_btn_back);
		ptrListView = (PullToRefreshListView) findViewById(R.id.plan_ptrListView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);
		lists = new ArrayList<Homework>();
		mHttpUtils = new HttpUtils();
		classId = loadId();
		adapter = new CommonAdapter<Homework>(this, R.layout.fragment_inform_item, lists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, Homework item) {
				viewHolder.setTextForTextView(R.id.inform_item_title, item.getH_title());
				viewHolder.setTextForTextView(R.id.inform_item_date, item.getH_date());
				
			}
		};
		ptrListView.setAdapter(adapter);
		ptrListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent intent = new Intent(PlanActivity.this, HomeWorkInfoActivity.class);
				Bundle bundle = new Bundle();
		        bundle.putSerializable("HomeWork1", lists.get(position-1));
		        intent.putExtras(bundle);
		        startActivity(intent);
			}
		});
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				PlanActivity.this.finish();
			}
		});
	}
	
	public void loadData() {
		url = ConstansUrl.GETHOMEWORK+classId;
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
						List<Homework> homeworks = JsonParseUtils.parseJsonHomeWork(data);
						lists.clear();
						lists.addAll(homeworks);
						mHandle.sendEmptyMessage(0x0010);
						
					}
					
				});
				
			}
			
		});
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		loadData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}
	
	public int loadId(){
		SharedPreferences stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		int id = stuOrTea.getInt("id", 0);
		return id;
	}
}
