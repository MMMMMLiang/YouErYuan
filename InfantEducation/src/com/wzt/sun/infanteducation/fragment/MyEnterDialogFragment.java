package com.wzt.sun.infanteducation.fragment;

import com.lidroid.xutils.HttpUtils;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MyEnterDialogFragment extends DialogFragment implements OnClickListener{
	private Button btn_exit;
	private Button btn_dis;
	private EditText et_enter;
	private HttpUtils mHttpUtils;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); 
		View view = inflater.inflate(R.layout.fragment_enter_item, container);
		et_enter = (EditText) view.findViewById(R.id.alert_enter_dialog_text2);
		btn_exit = (Button) view.findViewById(R.id.enter_dialog_exit);
		btn_dis = (Button) view.findViewById(R.id.enter_dialog_dis);
		mHttpUtils = new HttpUtils();
		
		btn_dis.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
        return view;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.exit_dialog_exit:
			String str = et_enter.getText().toString().trim();
			if(TextUtils.isEmpty(str)){
				BaseApp.getInstance().showToast("内容不能为空！");
			}else {
				
			}
			dismiss();
			break;
		case R.id.exit_dialog_dis:
			dismiss();
			break;

		default:
			break;
		}
	}
	
	private void updata(){
		
	}
}
