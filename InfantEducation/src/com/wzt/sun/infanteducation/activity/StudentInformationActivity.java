package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 学生信息显示
 * @author sun.ml
 *
 */
public class StudentInformationActivity extends BaseActivity implements OnClickListener{

	private ImageView iv;
	private ImageView iv_head;
	private TextView tv_name;
	private TextView tv_sex;
	private TextView tv_volk;
	private TextView tv_class;
	private TextView tv_id;
	private TextView tv_birthday;
	
	private String name;
	private String sex;
	private String volk;
	private int mClass;
	private int id;
	private String birthday;
	
	private SharedPreferences stuOrTea = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_student_info);

		initView();
		loadData();
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_btn_stuback);
		iv_head = (ImageView) findViewById(R.id.stu_info_low1_image);
		tv_name = (TextView) findViewById(R.id.stu_info_name);
		tv_sex = (TextView) findViewById(R.id.stu_info_sex);
		tv_volk = (TextView) findViewById(R.id.stu_info_volk);
		tv_class = (TextView) findViewById(R.id.stu_info_class);
		tv_id = (TextView) findViewById(R.id.stu_info_num);
		tv_birthday = (TextView) findViewById(R.id.stu_info_birthday);
		
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		iv.setOnClickListener(this);
		iv_head.setOnClickListener(this);
	}
	
	public void loadData(){
		name = stuOrTea.getString("st_name", null);
		sex = stuOrTea.getString("st_sex", null);
		volk = stuOrTea.getString("st_volk", null);
		mClass = stuOrTea.getInt("c_id", 0);
		id = stuOrTea.getInt("id", 0);
		birthday = stuOrTea.getString("st_birthday", null);
		
		tv_name.setText(name);
		tv_sex.setText(sex);
		tv_volk.setText(volk);
		tv_class.setText(mClass+"");
		tv_id.setText(id+"");
		tv_birthday.setText(birthday);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.titlebar_btn_stuback:
			StudentInformationActivity.this.finish();
			break;

		case R.id.stu_info_low1_image:
			Intent intent = new Intent(this, FaviconActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
