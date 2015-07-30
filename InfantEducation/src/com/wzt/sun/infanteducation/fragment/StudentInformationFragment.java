package com.wzt.sun.infanteducation.fragment;

import com.wzt.sun.infanteducation.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 这是学生信息页面
 * @author sun
 *
 */
public class StudentInformationFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_stu_information, null);
		return view;
	}

}
