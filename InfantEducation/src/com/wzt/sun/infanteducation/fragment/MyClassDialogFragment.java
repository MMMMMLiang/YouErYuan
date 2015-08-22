package com.wzt.sun.infanteducation.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Classes;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;
import com.wzt.sun.infanteducation.view.MyListView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MyClassDialogFragment extends DialogFragment implements OnItemClickListener{
	
	private MyListView mListView;
	private ArrayAdapter<String> adapter;
	private HttpUtils mHttpUtils;
	
	private static ArrayList<String> lists;
	private ArrayList<String> clists;
	private List<Classes> claLists;
	
	private FaCallBack callback;
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0020) {
				adapter.notifyDataSetChanged();
			}
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = inflater.inflate(R.layout.fragment_choose_class_item, container);
		
		mListView = (MyListView) view.findViewById(R.id.cClass_listview);
		mHttpUtils = new HttpUtils();
		lists = new ArrayList<String>();
		claLists = new ArrayList<Classes>();
		clists = new ArrayList<String>();
		
		initStr();
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lists);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
		return view;
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
				claLists.addAll(clss);
				//lists.add("全部");
				for (int i = 0; i < claLists.size(); i++) {
					ArrayList<String> clsNames = new ArrayList<String>();
					clsNames.add(claLists.get(i).getC_name());
					clists.addAll(clsNames);
				}
				lists.addAll(clists);
				mHandle.sendEmptyMessage(0x0020);
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		//BaseApp.getInstance().showToast(position+"");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clsId", claLists.get(position).getC_id());
		map.put("clsName", claLists.get(position).getC_name());
		callback.getData(map);
		dismiss();
	}
	
	public interface FaCallBack{
		public void getData(Map<String, Object> map);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			callback = (FaCallBack) activity;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
