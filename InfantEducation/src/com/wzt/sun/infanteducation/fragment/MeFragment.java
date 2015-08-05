package com.wzt.sun.infanteducation.fragment;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.FaviconActivity;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.CircleTransform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MeFragment extends Fragment implements OnItemClickListener{
	
	private ImageView iv;
	
	//加载菜单
	private ListView mListView;
	private SimpleAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_first_me, null);
		mListView = (ListView) view.findViewById(R.id.fragment_me_lv);
		adapter = new SimpleAdapter(getActivity(), ConstantsConfig.loadMyMenu(), R.layout.fragment_me_listview_item, 
				new String[] { "itemImage", "itemText" }, new int[] {R.id.me_lv_item_image, R.id.me_lv_item_text});
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
		iv = (ImageView) view.findViewById(R.id.iv_usercenter_avatar);
		
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), FaviconActivity.class);
				startActivity(intent);
			}
		});
		Picasso.with(getActivity()).load(R.drawable.head_icon).transform(new CircleTransform()).into(iv);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		BaseApp.getInstance().showToast("你点击了："+position);
	}

}
