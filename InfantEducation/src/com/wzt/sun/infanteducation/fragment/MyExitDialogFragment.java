package com.wzt.sun.infanteducation.fragment;

import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

public class MyExitDialogFragment extends DialogFragment implements OnClickListener{
	private Button btn_exit;
	private Button btn_dis;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); 
		View view = inflater.inflate(R.layout.fragment_exit_item, container);
		btn_exit = (Button) view.findViewById(R.id.exit_dialog_exit);
		btn_dis = (Button) view.findViewById(R.id.exit_dialog_dis);
		
		btn_dis.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
        return view;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.exit_dialog_exit:
			MainActivity.instance.finish();
			dismiss();
			break;
		case R.id.exit_dialog_dis:
			dismiss();
			break;

		default:
			break;
		}
	}
}
