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
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.adapter.CommonAdapter;
import com.wzt.sun.infanteducation.adapter.CommonViewHolder;
import com.wzt.sun.infanteducation.bean.StarBaby;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 明星宝宝页面
 * @author sun
 *
 */
public class StarActivity extends BaseActivity {
	
	private ImageView iv;
	
	//管理线程,保证始终只开一个线程
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private List<StarBaby> lists;
	private CommonAdapter<StarBaby> adapter;
	private ListView starListView;
	private HttpUtils mHttpUtils;
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0007) {
				adapter.notifyDataSetChanged();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star);
		
		initView();
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				StarActivity.this.finish();
			}
		});
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_star_btn_back);
		lists = new ArrayList<StarBaby>();
		mHttpUtils = new HttpUtils();
		starListView = (ListView) findViewById(R.id.star_listview);
		loadData();
		adapter = new CommonAdapter<StarBaby>(this, R.layout.activity_star_item, lists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, StarBaby item) {
				// TODO Auto-generated method stub
				viewHolder.setCircleImageForView(StarActivity.this, R.id.star_item_icon1, item.getSb_img());
				viewHolder.setTextForTextView(R.id.star_item_text, item.getSb_content());
			}
		};
		starListView.setAdapter(adapter);
	}
	
	public void loadData(){
		executor.execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETSTAR, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						// TODO Auto-generated method stub
						String data = response.result;
						List<StarBaby> starBabys = JsonParseUtils.parseJsonStarBaby(data);
						lists.addAll(starBabys);
						mHandle.sendEmptyMessage(0x0007);
					}
				});
			}
			
		});
	}

}
