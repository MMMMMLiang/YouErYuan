package com.wzt.sun.infanteducation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;

public class MeFragment extends Fragment {
	
	private ImageView iv;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_first_me, null);
		iv = (ImageView) view.findViewById(R.id.iv_usercenter_avatar);
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast("点击了头像");
			}
		});
		return view;
	}
	
}
