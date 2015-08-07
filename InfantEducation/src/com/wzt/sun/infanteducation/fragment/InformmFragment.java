package com.wzt.sun.infanteducation.fragment;

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
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.InformInfoActivity;
import com.wzt.sun.infanteducation.adapter.CommonAdapter;
import com.wzt.sun.infanteducation.adapter.CommonViewHolder;
import com.wzt.sun.infanteducation.bean.Inform;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class InformmFragment extends Fragment implements OnRefreshListener2<ListView>{
	private PullToRefreshListView ptrListView;
	private CommonAdapter<Inform> adapter;
	private List<Inform> lists;
	private HttpUtils mHttpUtils;
	private String type;
	private String url;
	
	//管理线程,保证始终只开一个线程
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0005) {
				//ptrListView.onRefreshComplete();
				adapter.notifyDataSetChanged();
			}
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_first_inform, null);
		ptrListView = (PullToRefreshListView) view.findViewById(R.id.inform_ptrListView);
		ptrListView.setMode(Mode.PULL_FROM_START);
		ptrListView.setOnRefreshListener(this);
		initView(view);
		
		return view;
	}
	
	private void initView(View view){
		
		lists = new ArrayList<Inform>();
		mHttpUtils = new HttpUtils();
		type = "1";
		url = ConstansUrl.INFORMURL+type;
		loadData();
		adapter = new CommonAdapter<Inform>(getActivity(), R.layout.fragment_inform_item, lists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, Inform item) {
				// TODO Auto-generated method stub
				viewHolder.setTextForTextView(R.id.inform_item_title, item.getN_title());
				viewHolder.setTextForTextView(R.id.inform_item_date, item.getN_date());
			}
		};
		ptrListView.setAdapter(adapter);
		ptrListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent intent = new Intent(getActivity(), InformInfoActivity.class);
				Bundle bundle = new Bundle();
		        bundle.putSerializable("InformInfo1", lists.get(position));
		        intent.putExtras(bundle);
		        startActivity(intent);
			}
		});
	}

	private void loadData() {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				mHttpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						String data = response.result;
						List<Inform> informs = JsonParseUtils.parseJsonInform(data);
						lists.addAll(informs);
						mHandle.sendEmptyMessage(0x0005);
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
