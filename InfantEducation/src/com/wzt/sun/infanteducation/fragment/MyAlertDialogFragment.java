package com.wzt.sun.infanteducation.fragment;

import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

public class MyAlertDialogFragment extends DialogFragment implements OnClickListener{
	private Button btn_login;
	private Button btn_dis;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); 
		View view = inflater.inflate(R.layout.fragment_login_dialog, container);
		btn_login = (Button) view.findViewById(R.id.alert_dialog_login);
		btn_dis = (Button) view.findViewById(R.id.alert_dialog_dis);
		
		btn_dis.setOnClickListener(this);
		btn_login.setOnClickListener(this);
        return view;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.alert_dialog_login:
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
			MainActivity.instance.finish();
			dismiss();
			break;
		case R.id.alert_dialog_dis:
			dismiss();
			break;

		default:
			break;
		}
	}
	
	
	/*@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialog_no_border);  
		// Get the layout inflater  
		LayoutInflater inflater = getActivity().getLayoutInflater();  
		View view = inflater.inflate(R.layout.fragment_login_dialog, null);  
		// Inflate and set the layout for the dialog  
		// Pass null as the parent view because its going in the dialog layout  
		builder.setView(view)
		// Add action buttons  
		.setPositiveButton("登录", new DialogInterface.OnClickListener(){  
			
			@Override  
			public void onClick(DialogInterface dialog, int id){ 
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent);
				dismiss();
			}  
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				dismiss();
			}
		});  
		return builder.create();
	}*/
}
