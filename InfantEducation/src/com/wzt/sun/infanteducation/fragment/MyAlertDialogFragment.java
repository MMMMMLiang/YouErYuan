package com.wzt.sun.infanteducation.fragment;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.LoginActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class MyAlertDialogFragment extends DialogFragment {

	@Override
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
	}
}
