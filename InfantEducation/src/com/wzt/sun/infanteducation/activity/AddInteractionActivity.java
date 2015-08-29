package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class AddInteractionActivity extends BaseActivity {
	
	private EditText et_text;
	//private SharedPreferences userInfo = null;
	private SharedPreferences stuOrTea = null;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_interaction);
		initView();
		
	}
	
	public void initView(){
		et_text = (EditText) findViewById(R.id.addinter_et);
		//userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		id = stuOrTea.getInt("id", 0);

	}
	
	public void ivClick(View view){
		switch (view.getId()) {
		case R.id.titlebar_addinter_btn_back:
			// 回退btn
			AddInteractionActivity.this.finish();
			break;
		case R.id.titlebar_addinter_btn_add:
			// 发表btn
			if(TextUtils.isEmpty(et_text.getText().toString())){
				BaseApp.getInstance().showToast("内容不能为空！");
			}else {
				
			}
			
			// 清空EditText
			et_text.setText("");
			break;

		default:
			break;
		}
	}
}
