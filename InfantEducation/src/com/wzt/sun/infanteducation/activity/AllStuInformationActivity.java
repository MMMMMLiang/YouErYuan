package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.adapter.CommonAdapter;
import com.wzt.sun.infanteducation.adapter.CommonViewHolder;
import com.wzt.sun.infanteducation.bean.Student;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.fragment.MyClassDialogFragment;
import com.wzt.sun.infanteducation.fragment.MyClassDialogFragment.FaCallBack;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 园长查询所有学生信息
 * @author sun.ml
 *
 */
public class AllStuInformationActivity extends FragmentActivity implements FaCallBack{

	private ListView mListView;
	private ImageView iv;
	private List<Student> lists;
	private HttpUtils mHttpUtils;
	private CommonAdapter<Student> adapter;
	private TextView tv_cClass;

	private SharedPreferences loginSp = null;
	private boolean isLea = false;

	private String c_id;
	private String cName;
	private String url;

	private Handler mHandle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0015) {
				adapter.notifyDataSetChanged();
			}else if (msg.what == 0x0016) {
				tv_cClass.setText(cName);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_stu_info);

		initView();
	}

	public void initView() {
		mListView = (ListView) findViewById(R.id.all_stu_listview);
		iv = (ImageView) findViewById(R.id.titlebar_allstu_btn_back);
		tv_cClass = (TextView) findViewById(R.id.btn_tv_choose_class);
		lists = new ArrayList<Student>();
		mHttpUtils = new HttpUtils();
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		isLea = loginSp.getBoolean("isLeader", false);
		choose();
		
		adapter = new CommonAdapter<Student>(this, R.layout.activity_all_stu_infi_item, lists) {

			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, Student item) {
				viewHolder.setImageForFoodView(AllStuInformationActivity.this, R.id.allstu_item_icon,
						item.getSt_photo());
				viewHolder.setTextForTextView(R.id.allstu_item_name, item.getSt_name());
				viewHolder.setTextForTextView(R.id.allstu_item_sex, item.getSt_sex());
				viewHolder.setTextForTextView(R.id.allstu_item_id, item.getSt_id() + "");
			}
		};
		mListView.setAdapter(adapter);
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AllStuInformationActivity.this.finish();
			}
		});
		tv_cClass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				dialogShow();
			}
		});
		
	}
	
	private void choose(){
		if(!isLea){
			tv_cClass.setVisibility(View.GONE);
			loadData();
		}
	}

	public void loadData() {
		if(!isLea){
			c_id = loadC_id()+"";
		}
		url = ConstansUrl.GETALLSTU + c_id;
		mHttpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast(arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> response) {
				// TODO Auto-generated method stub
				lists.clear();
				String data = response.result;
				List<Student> stus = JsonParseUtils.parseJsonStudents(data);
				lists.addAll(stus);
				mHandle.sendEmptyMessage(0x0015);
			}
		});
	}

	public int loadC_id() {
		SharedPreferences stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		int cId = stuOrTea.getInt("c_id", 0);
		return cId;
	}

	@Override
	public void getData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		c_id =  map.get("clsId")+"";
		cName = (String) map.get("clsName");
		loadData();
		
		mHandle.sendEmptyMessage(0x0016);
	}
	
	private void dialogShow() {
		DialogFragment newFragment = new MyClassDialogFragment();
		newFragment.show(getSupportFragmentManager(), "exitDialog");
	}
}
