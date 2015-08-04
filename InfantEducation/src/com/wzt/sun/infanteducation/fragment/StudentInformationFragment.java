package com.wzt.sun.infanteducation.fragment;

import com.wzt.sun.infanteducation.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 这是学生信息页面
 * @author sun
 *
 */
public class StudentInformationFragment extends BaseFragment {
	
	private ImageView iv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_stu_information, null);
		iv = (ImageView) view.findViewById(R.id.titlebar_btn_stuback);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getFragmentManager().popBackStack();
			}
		});
		return view;
	}

}
