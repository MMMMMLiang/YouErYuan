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
 * 教师信息显示
 * @author Administrator
 *
 */
public class TeacherInformationActivity extends BaseActivity implements OnClickListener{
	
	private ImageView iv;
	private ImageView iv_head;
	private TextView tv_name;
	private TextView tv_sex;
	private TextView tv_volk;
	private TextView tv_class;
	private TextView tv_id;
	private TextView tv_time;
	private TextView tv_lv;
	private TextView tv_address;
	private TextView tv_parentsphone;
	
	private String name;
	private String sex;
	private String volk;
	private int mClass;
	private int id;
	private String time;
	private String lv;
	private String address;
	private String parentsphone;
	
	private SharedPreferences stuOrTea = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_teacher_info);
		
		initView();
		loadData();
	}
	
	public void initView(){
		iv = (ImageView) findViewById(R.id.titlebar_btn_teaback);
		iv_head = (ImageView) findViewById(R.id.tea_info_low1_image);
		tv_name = (TextView) findViewById(R.id.tea_info_name);
		tv_sex = (TextView) findViewById(R.id.tea_info_sex);
		tv_volk = (TextView) findViewById(R.id.tea_info_volk);
		tv_class = (TextView) findViewById(R.id.tea_info_class);
		tv_id = (TextView) findViewById(R.id.tea_info_num);
		tv_time = (TextView) findViewById(R.id.tea_info_birthday);
		tv_lv = (TextView) findViewById(R.id.tea_info_parents);
		tv_address = (TextView) findViewById(R.id.tea_info_address);
		tv_parentsphone = (TextView) findViewById(R.id.tea_info_parentsphone);
		
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		iv.setOnClickListener(this);
		iv_head.setOnClickListener(this);
	}
	
	public void loadData(){
		name = stuOrTea.getString("t_name", null);
		sex = stuOrTea.getString("t_sex", null);
		volk = stuOrTea.getString("t_volk", null);
		mClass = stuOrTea.getInt("c_id", 0);
		id = stuOrTea.getInt("id", 0);
		time = stuOrTea.getString("t_date", null);
		lv = stuOrTea.getString("t_lv", null);
		address = stuOrTea.getString("t_address", null);
		parentsphone = stuOrTea.getString("t_phone", null);
		
		tv_name.setText(name);
		tv_sex.setText(sex);
		tv_volk.setText(volk);
		tv_class.setText(mClass+"");
		tv_id.setText(id+"");
		tv_time.setText(time);
		tv_lv.setText(lv);
		tv_address.setText(address);
		tv_parentsphone.setText(parentsphone);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.titlebar_btn_teaback:
			TeacherInformationActivity.this.finish();
			break;

		case R.id.tea_info_low1_image:
			Intent intent = new Intent(this, FaviconActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
