package com.wzt.sun.infanteducation.fragment;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.adapter.MyListItemAdapter;
import com.wzt.sun.infanteducation.bean.ItemEntity;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 互动fragment
 * @author sun.ml
 *
 */
public class InteractionFragment extends Fragment {
	private PullToRefreshListView ptrListView;
	private HttpUtils mHttpUtils;
	private List<ItemEntity> lists;
	private MyListItemAdapter adapter;
	private int c_id;
	private String url;
	private SharedPreferences stuOrTea = null;
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0023) {
				ptrListView.onRefreshComplete();
				adapter.notifyDataSetChanged();
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_first_interaction, null);
		
		initView(view);
		return view;
	}
	
	private void initView(View view){
		ptrListView = (PullToRefreshListView) view.findViewById(R.id.fragment_first_interaction_ptr);
		mHttpUtils = new HttpUtils();
		lists = new ArrayList<ItemEntity>();
		stuOrTea = getActivity().getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, Context.MODE_PRIVATE);
		c_id = stuOrTea.getInt("c_id", 0);
		
		adapter = new MyListItemAdapter(getActivity(), lists);
		ptrListView.setAdapter(adapter);
		loadData();
	}
	
	private void loadData(){
		url = ConstansUrl.GETALLINTER + c_id;
		mHttpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast(arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> response) {
				String data = response.result;
				List<ItemEntity> itemEntitys = JsonParseUtils.parseJsonInteraction(data);
				lists.addAll(itemEntitys);
				mHandle.sendEmptyMessage(0x0023);
			}
		});
	}

}
