package com.wzt.sun.infanteducation.activity;

import java.util.List;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Student;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.CircleTransform;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
	private TextView tv_address;
	private TextView tv_hea;
	private TextView tv_moPhone;
	private TextView tv_faPhone;
	
	private Button btn_father;
	private Button btn_mother;
	private boolean isParents;
	
	private String name;
	private String sex;
	private String volk;
	private int mClass;
	private int id;
	private String birthday;
	private String address;
	private String hea;
	private String spfUrl;
	private String moPhone;
	private String faPhone;
	private HttpUtils mHttpUtils;
	
	private SharedPreferences stuOrTea = null;
	private SharedPreferences userInfo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_student_info);
		
		initView();
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
		tv_address = (TextView) findViewById(R.id.stu_info_address);
		tv_hea = (TextView) findViewById(R.id.stu_info_hea);
		tv_moPhone = (TextView) findViewById(R.id.stu_info_mother_phone);
		tv_faPhone = (TextView) findViewById(R.id.stu_info_father_phone);
		btn_mother = (Button) findViewById(R.id.stu_info_btn1);
		btn_father = (Button) findViewById(R.id.stu_info_btn2);
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		spfUrl = stuOrTea.getString("photo", null);
		isParents = userInfo.getBoolean("isParent", false);
		Picasso.with(this).load(ConstansUrl.getHeadnUrl(spfUrl)).placeholder(R.drawable.avatar).error(R.drawable.avatar).transform(new CircleTransform()).into(iv_head);
		iv.setOnClickListener(this);
		if(isParents){
			iv_head.setOnClickListener(this);
			Intent mIntent = getIntent();
			Bundle bundle = mIntent.getExtras();
			id = bundle.getInt("STUID");
			btn_mother.setVisibility(View.GONE);
			btn_father.setVisibility(View.GONE);
			loadData();
		}else {
			Intent mIntent = getIntent();
			Bundle bundle = mIntent.getExtras();
			id = bundle.getInt("STUIDFORMALL");
			btn_mother.setOnClickListener(this);
			btn_father.setOnClickListener(this);
			mHttpUtils = new HttpUtils();
			mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETSTUDENTSINFO+id, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					BaseApp.getInstance().showToast(arg1);
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					String str = arg0.result;
					List<Student> stus = JsonParseUtils.parseJsonStudents(str);
					tv_name.setText(stus.get(0).getSt_name());
					tv_sex.setText(stus.get(0).getSt_sex());
					tv_volk.setText(stus.get(0).getSt_volk());
					tv_class.setText(stus.get(0).getC_id()+"");
					tv_id.setText(stus.get(0).getSt_id()+"");
					tv_birthday.setText(stus.get(0).getSt_birthday());
					tv_address.setText(stus.get(0).getSt_address());
					tv_hea.setText(stus.get(0).getSt_health());
					tv_moPhone.setText(stus.get(0).getSt_mcard());
					tv_faPhone.setText(stus.get(0).getSt_fcard());
					String headUrl = stus.get(0).getSt_photo();
					Picasso.with(StudentInformationActivity.this).load(ConstansUrl.getHeadnUrl(headUrl)).placeholder(R.drawable.avatar).error(R.drawable.avatar).transform(new CircleTransform()).into(iv_head);
				}
			});
			
		}
	}
	
	public void loadData(){
		name = stuOrTea.getString("st_name", null);
		sex = stuOrTea.getString("st_sex", null);
		volk = stuOrTea.getString("st_volk", null);
		mClass = stuOrTea.getInt("c_id", 0);
		//id = stuOrTea.getInt("id", 0);
		birthday = stuOrTea.getString("st_birthday", null);
		address = stuOrTea.getString("st_address", null);
		hea = stuOrTea.getString("st_health", null);
		moPhone = stuOrTea.getString("st_mcard", null);
		faPhone = stuOrTea.getString("st_fcard", null);
		tv_name.setText(name);
		tv_sex.setText(sex);
		tv_volk.setText(volk);
		tv_class.setText(mClass+"");
		tv_id.setText(id+"");
		tv_birthday.setText(birthday);
		tv_address.setText(address);
		tv_hea.setText(hea);
		tv_moPhone.setText(moPhone);
		tv_faPhone.setText(faPhone);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		String phone = null;
		switch (view.getId()) {
		case R.id.titlebar_btn_stuback:
			StudentInformationActivity.this.finish();
			break;
		case R.id.stu_info_low1_image:
			Intent intent = new Intent(this, FaviconActivity.class);
			startActivity(intent);
			break;
		case R.id.stu_info_btn1:
			phone = tv_moPhone.getText().toString().trim();
			Intent mIntent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone));
            startActivity(mIntent);
			break;
		case R.id.stu_info_btn2:
			phone = tv_moPhone.getText().toString().trim();
			Intent nIntent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone));
            startActivity(nIntent);
			break;

		default:
			break;
		}
	}
}
