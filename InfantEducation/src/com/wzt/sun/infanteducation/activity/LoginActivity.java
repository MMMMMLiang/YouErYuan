package com.wzt.sun.infanteducation.activity;

import java.util.List;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Student;
import com.wzt.sun.infanteducation.bean.Teacher;
import com.wzt.sun.infanteducation.bean.User;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private EditText et_login_username;
	private EditText et_login_password;
	
	private HttpUtils mHttpUtils;
	private static String log;
	private static String id;
	
	private SharedPreferences loginSp = null;
	private SharedPreferences userInfo = null;
	private SharedPreferences stuOrTea = null;
	
	private String userName;
	private String passWord;
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x00001:
				saveStuOrTea();
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}
	
	public void initView() {
		et_login_username = (EditText) findViewById(R.id.et_login_username);
		et_login_password = (EditText) findViewById(R.id.et_login_password);
		mHttpUtils = new HttpUtils();
	}
	
	public void btnClick(View view){
		userName = et_login_username.getText().toString();
		passWord = et_login_password.getText().toString();
		switch (view.getId()) {
		case R.id.btn_login_login:
			//获取用户名、密码
			if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)){
				BaseApp.getInstance().showToast("用户名、密码不能为空！");
			}else {

				RequestParams params = new RequestParams();
				params.addQueryStringParameter("user", userName);
				params.addQueryStringParameter("pwd", passWord);
				mHttpUtils.send(HttpMethod.POST, ConstansUrl.loginUrl(), params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						BaseApp.getInstance().showToast(arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						String str = responseInfo.result;
						List<User> users = JsonParseUtils.parseJsonUser(str);
						id = users.get(0).getRemarks2().toString();
						saveUserInfo(str);
						BaseApp.getInstance().showToast("success");
						//保存登录状态
						loadLogin();
						mHandle.sendEmptyMessage(0x00001);
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(intent);
						finish();

					}
				});


			}
			break;

		default:
			break;
		}
	}
	
	/**
	 * 保存登录状态
	 */
	private void loadLogin() {
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		Editor editor=loginSp.edit();
		editor.putBoolean("isLogin", true);
		editor.commit();
	}
	
	/**
	 * 保存用户信息
	 */
	private void saveUserInfo(String str){
		List<User> users = JsonParseUtils.parseJsonUser(str);
		userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		Editor editor=userInfo.edit();
		
		editor.putInt("vid", users.get(0).getVid());
		editor.putInt("jifen", users.get(0).getJifen());
		log = users.get(0).getVsf();
		editor.putString("vsf", log);
		editor.putString("vsfname", users.get(0).getVsfname());
		editor.putString("user", users.get(0).getUser());
		editor.putString("password", users.get(0).getPassword());
		editor.putString("name", users.get(0).getName());
		editor.putString("phone", users.get(0).getPhone());
		editor.putString("registerdate", users.get(0).getRegisterdate());
		editor.putString("state", users.get(0).getState());
		editor.putString("identity", users.get(0).getIdentity());
		editor.putString("email", users.get(0).getEmail());
		editor.putString("address", users.get(0).getAddress());
		editor.putString("appointmenttime", users.get(0).getAppointmenttime());
		editor.putString("remarks1", users.get(0).getRemarks1());
		String ids = users.get(0).getRemarks2();
		editor.putString("remarks2", ids);
		if(log.equals("A")){
			// 是家长
			editor.putBoolean("isParent", true);
			editor.putBoolean("isTeacher", false);
			editor.putBoolean("isLeader", false);
		}else if (log.equals("B")) {
			// 是老师
			editor.putBoolean("isParent", false);
			editor.putBoolean("isTeacher", true);
			editor.putBoolean("isLeader", false);
		}else if(log.equals("C")){
			// 是园长
			editor.putBoolean("isParent", false);
			editor.putBoolean("isTeacher", false);
			editor.putBoolean("isLeader", true);
		}
		
		editor.commit();
	}
	
	/**
	 * 保存学生或老师信息
	 */
	private void saveStuOrTea(){
		if("A".equals(log)){
			// 家长
			mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETSTUDENTSINFO+id, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					// TODO Auto-generated method stub
					saveStuInfo(responseInfo.result);
				}
			});
		}else if ("B".equals(log)) {
			// 老师
			mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETTEACHERSINFO+id, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					// TODO Auto-generated method stub
					saveTeaInfo(responseInfo.result);
				}
			});
		}
		
	}
	
	/**
	 * 保存学生信息
	 */
	private void saveStuInfo(String str){
		List<Student> stus = JsonParseUtils.parseJsonStudents(str);
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		Editor editor=stuOrTea.edit();
		editor.clear();
		
		editor.putInt("id", stus.get(0).getSt_id());
		editor.putInt("c_id", stus.get(0).getC_id());
		editor.putString("st_name", stus.get(0).getSt_name());
		editor.putString("st_sex", stus.get(0).getSt_sex());
		editor.putString("st_volk", stus.get(0).getSt_volk());
		editor.putString("st_birthday", stus.get(0).getSt_birthday());
		editor.putString("st_date", stus.get(0).getSt_date());
		editor.putString("st_card", stus.get(0).getSt_card());
		editor.putString("st_address", stus.get(0).getSt_address());
		editor.putString("st_father", stus.get(0).getSt_father());
		editor.putString("st_fcard", stus.get(0).getSt_fcard());
		editor.putString("st_mother", stus.get(0).getSt_mother());
		editor.putString("st_mcard", stus.get(0).getSt_mcard());
		editor.putString("st_health", stus.get(0).getSt_health());
		editor.putString("st_hremarks", stus.get(0).getSt_hremarks());
		editor.putString("st_shuttlecard", stus.get(0).getSt_shuttlecard());
		editor.putString("st_shuttle", stus.get(0).getSt_shuttle());
		editor.putString("st_graduated", stus.get(0).getSt_graduated());
		editor.putString("photo", stus.get(0).getSt_photo());
		
		editor.commit();
	}
	
	/**
	 * 保存教师信息
	 */
	private void saveTeaInfo(String str){
		List<Teacher> teas = JsonParseUtils.parseJsonTeacher(str);
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		Editor editor=stuOrTea.edit();
		editor.clear();
		
		editor.putInt("id", teas.get(0).getT_id());
		editor.putString("t_name", teas.get(0).getT_name());
		editor.putString("t_sex", teas.get(0).getT_sex());
		editor.putString("t_lv", teas.get(0).getT_lv());
		editor.putString("t_date", teas.get(0).getT_date());
		editor.putString("t_volk", teas.get(0).getT_volk());
		editor.putString("t_job", teas.get(0).getT_job());
		editor.putString("t_title", teas.get(0).getT_title());
		editor.putString("t_phone", teas.get(0).getT_phone());
		editor.putString("t_card", teas.get(0).getT_card());
		editor.putString("t_address", teas.get(0).getT_address());
		editor.putString("t_we", teas.get(0).getT_we());
		editor.putString("photo", teas.get(0).getT_img());
		editor.putString("t_type", teas.get(0).getT_type());
		editor.putInt("c_id", teas.get(0).getC_id());
		
		editor.commit();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//lists.clear();
			//点击返回键跳转到主界面
			Intent intent=new Intent(this,MainActivity.class);
			startActivity(intent);
		}
		return super.onKeyDown(keyCode, event);
	}

}
