package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.List;

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
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 教师添加考勤信息
 * @author sun
 *
 */
public class AddLogBookActivity extends BaseActivity {
	private SharedPreferences stuOrTea = null;
	private int c_id;
	private ListView mListView;
	private ImageView iv;
	private List<Student> lists;
	private HttpUtils mHttpUtils;
	private CommonAdapter<Student> adapter;
	
	private Handler mHandle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0027) {
				adapter.notifyDataSetChanged();
			}
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_logbook);
		
		initView();
		loadData();
	}
	
	public void initView(){
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		c_id = stuOrTea.getInt("c_id", 0);
		mListView = (ListView) findViewById(R.id.add_lb_listview);
		iv = (ImageView) findViewById(R.id.titlebar_addlb_btn_back);
		lists = new ArrayList<Student>();
		mHttpUtils = new HttpUtils();
		adapter = new CommonAdapter<Student>(AddLogBookActivity.this, R.layout.activity_add_logbook_item, lists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, Student item) {
				viewHolder.setTextForTextView(R.id.addlb_item_class, lists.get(position).getC_id()+"");
				viewHolder.setTextForTextView(R.id.addlb_item_name, lists.get(position).getSt_name());
				viewHolder.setTextForTextView(R.id.addlb_item_id, lists.get(position).getSt_id()+"");
			}
		};
		mListView.setAdapter(adapter);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AddLogBookActivity.this.finish();
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast(position+"");
			}
		});
	}
	
	public void loadData(){
		mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETALLSTU + c_id, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> response) {
				lists.clear();
				String data = response.result;
				List<Student> stus = JsonParseUtils.parseJsonStudents(data);
				lists.addAll(stus);
				mHandle.sendEmptyMessage(0x0027);
			}
			
		});
	}
}
