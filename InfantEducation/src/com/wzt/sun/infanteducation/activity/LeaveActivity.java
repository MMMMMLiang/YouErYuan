package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;

import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.view.CustomerSpinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class LeaveActivity extends Activity {
	
	private ImageView iv;
	
	private CustomerSpinner mSpinner;
	private static ArrayList<String> lists;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_leave);
		initStr();
		initView();
	}
	
	public void initView() {
		iv = (ImageView) findViewById(R.id.titlebar_leave_btn_back);
		mSpinner = (CustomerSpinner) findViewById(R.id.leave_spinner);
		mSpinner.setList(lists);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lists);
		mSpinner.setAdapter(adapter);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LeaveActivity.this.finish();
			}
		});
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				BaseApp.getInstance().showToast(lists.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}
	
	/**
	 * 加载spinner下拉框数据
	 */
	public void initStr(){
		lists = new ArrayList<String>();
    	lists.add("病假");
    	lists.add("事假");
    	lists.add("其他");
    }

}
