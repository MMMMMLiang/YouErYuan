package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.adapter.CommonAdapter;
import com.wzt.sun.infanteducation.adapter.CommonViewHolder;
import com.wzt.sun.infanteducation.bean.News;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class SchoolNewsActivity extends BaseActivity implements OnRefreshListener2<ListView>{
	private PullToRefreshListView ptrListView;
	private CommonAdapter<News> adapter;
	private List<News> lists;
	private HttpUtils mHttpUtils;
	private ImageView iv;
	
	//管理线程,保证始终只开一个线程
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0025) {
				ptrListView.onRefreshComplete();
				adapter.notifyDataSetChanged();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school_news);
		
		initView();
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_schoolnews_btn_back);
		ptrListView = (PullToRefreshListView) findViewById(R.id.school_news_ptrListView);
		ptrListView.setMode(Mode.PULL_FROM_START);
		ptrListView.setOnRefreshListener(this);
		lists = new ArrayList<News>();
		mHttpUtils = new HttpUtils();
		loadData();
		adapter = new CommonAdapter<News>(this, R.layout.activity_school_news_item, lists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, News item) {
				viewHolder.setTextForTextView(R.id.scnews_item_title, item.getN_title());
				viewHolder.setTextForTextView(R.id.scnews_item_data, item.getN_date());
				viewHolder.setImageForView(SchoolNewsActivity.this, R.id.scnews_item_iv, item.getN_img());
			}
		};
		ptrListView.setAdapter(adapter);
		ptrListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				News mNews = lists.get(position-1);
				Intent intent = new Intent(SchoolNewsActivity.this,SchoolNewsInfoActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("NEWS", mNews);
				intent.putExtras(bundle);
				startActivity(intent);

			}
			
		});
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SchoolNewsActivity.this.finish();
			}
		});
	}
	
	public void loadData(){
		executor.execute(new Runnable() {

			@Override
			public void run() {
				mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETSCHOOLNEWS, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast(arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						// TODO Auto-generated method stub
						String data = response.result;
						List<News> news = JsonParseUtils.parseJsonSchoolNews(data);
						lists.clear();
						lists.addAll(news);
						mHandle.sendEmptyMessage(0x0025);
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
}
