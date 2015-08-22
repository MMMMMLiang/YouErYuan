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
import com.wzt.sun.infanteducation.bean.Teacher;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;
import com.wzt.sun.infanteducation.view.MyListView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 园长查询所有教师信息
 * @author sun.ml
 *
 */
public class AllTeacherInfo extends BaseActivity {
	
	private MyListView mListView;
	private List<Teacher> lists;
	private CommonAdapter<Teacher> adapter;
	private ImageView iv;
	private HttpUtils mHttpUtils;
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x0016){
				adapter.notifyDataSetChanged();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_tea_info);
		
		initView();
		loadData();
	}
	
	public void initView(){
		mListView = (MyListView) findViewById(R.id.all_tea_listview);
		lists = new ArrayList<Teacher>();
		mHttpUtils = new HttpUtils();
		
		adapter = new CommonAdapter<Teacher>(this, R.layout.activity_all_stu_infi_item, lists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, Teacher item) {
				// TODO Auto-generated method stub
				viewHolder.setImageForFoodView(AllTeacherInfo.this, R.id.allstu_item_icon, item.getT_img());
				viewHolder.setTextForTextView(R.id.allstu_item_name, item.getT_name());
				viewHolder.setTextForTextView(R.id.allstu_item_sex, item.getT_sex());
				viewHolder.setTextForTextView(R.id.allstu_item_id, item.getT_id()+"");
			}
		};
		mListView.setAdapter(adapter);
		
		iv = (ImageView) findViewById(R.id.titlebar_alltea_btn_back);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AllTeacherInfo.this.finish();
			}
		});
		
	}
	
	public void loadData(){
		mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETALLTEA, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast(arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> response) {
				// TODO Auto-generated method stub
				String data = response.result;
				List<Teacher> teas = JsonParseUtils.parseJsonTeacher(data);
				lists.addAll(teas);
				mHandle.sendEmptyMessage(0x0016);
			}
		});
	}
}
