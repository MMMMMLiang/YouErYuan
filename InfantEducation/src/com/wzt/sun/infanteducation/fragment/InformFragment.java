package com.wzt.sun.infanteducation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.InteractionFragmentActivity;

public class InformFragment extends Fragment implements OnClickListener{
	
	private RelativeLayout rl1;
	private RelativeLayout rl2;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_first_inform, null);
		
		rl1 = (RelativeLayout) view.findViewById(R.id.imteraction_rl1);
		rl2 = (RelativeLayout) view.findViewById(R.id.imteraction_rl2);

		rl1.setOnClickListener(this);
		rl2.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.imteraction_rl1:
			intent.setClass(getActivity(), InteractionFragmentActivity.class);
			startActivity(intent);
			break;
		case R.id.imteraction_rl2:
			intent.setClass(getActivity(), InteractionFragmentActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
