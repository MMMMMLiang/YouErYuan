package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;

import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.view.CustomerSpinner;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * 评价老师界面
 * @author sun
 *
 */
public class EvaluateActivity extends BaseActivity{

	private ImageView iv;
	
	private CustomerSpinner teaSpinner;
	private CustomerSpinner evaSpinner;
	private static ArrayList<String> tlists;
	private static ArrayList<String> elists;
	private ArrayAdapter<String> tAdapter;
	private ArrayAdapter<String> eAdapter;
	
	private String chooseTeacher;
	private boolean isChoose = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluate);
		
		initStr();
		initView();
		
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				EvaluateActivity.this.finish();
			}
		});
	}
	
	public void initView() {
		iv = (ImageView) findViewById(R.id.titlebar_eva_btn_back);
		teaSpinner = (CustomerSpinner) findViewById(R.id.evaluate_spinner_teacher);
		evaSpinner = (CustomerSpinner) findViewById(R.id.evaluate_spinner_star);
		teaSpinner.setList(tlists);
		evaSpinner.setList(elists);
		tAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tlists);
		teaSpinner.setAdapter(tAdapter);
		eAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, elists);
		evaSpinner.setAdapter(eAdapter);
		
		teaSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast(tlists.get(position));
				chooseTeacher = tlists.get(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		evaSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast(elists.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	/**
	 * 加载spinner下拉框数据
	 */
	public void initStr(){ 
		tlists = new ArrayList<String>();
    	tlists.add("教师1");
    	tlists.add("教师2");
    	tlists.add("教师3");
    	
    	elists = new ArrayList<String>();
    	elists.add("满意");
    	elists.add("不满意");
	}
	
	public void btnClick(View view){
		if(view.getId() == R.id.btn_present){
			String str = chooseTeacher;
			if(isChoose){
				BaseApp.getInstance().showToast("您已经提交过了");
			}else{
				BaseApp.getInstance().showToast("点击了btn");
				if(str.equals(chooseTeacher)){
					isChoose = true;
				}
			}
		}
	}

}
