package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.List;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.adapter.CommonAdapter;
import com.wzt.sun.infanteducation.adapter.CommonViewHolder;
import com.wzt.sun.infanteducation.bean.Education;
import com.wzt.sun.infanteducation.utils.ACache;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 教育教学详情界面
 * @author sun.ml
 *
 */
public class ConsultInfoActivity extends BaseActivity {
	private String[] strs;
	private ImageView iv;
	private TextView tv;
	private ACache mCache;
	private int type;
	
	private List<Education> lists;
	private List<Education> itemLists;
	private ListView mListView;
	private CommonAdapter<Education> adapter;
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0009) {
				adapter.notifyDataSetChanged();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consult_info);
		
		initView();
		loadData();
	}
	
	public void initView(){
		mCache = ACache.get(this);
		lists = new ArrayList<Education>();
		itemLists = new ArrayList<Education>();
		mListView = (ListView) findViewById(R.id.consult_info_listview);
		iv = (ImageView) findViewById(R.id.titlebar_eduinfo_btn_back);
		tv = (TextView) findViewById(R.id.titlebar_eduinfo_text);
		strs = new String[]{"游戏互动","体育培养","发现兴趣","语言培训"};
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		type =  bundle.getInt("EDU_ITEM");
		tv.setText(strs[type-1].toString());
		
		adapter = new CommonAdapter<Education>(this, R.layout.consult_edu_listview_item, itemLists) {
			
			@Override
			protected void fillItemData(CommonViewHolder viewHolder, int position, Education item) {
				viewHolder.setImageForView(ConsultInfoActivity.this, R.id.con_edu_item_icon, item.getSy_img());
				viewHolder.setTextForTextView(R.id.con_edu_item_tvtitle, item.getSy_title());
				viewHolder.setTextForTextView(R.id.con_edu_item_tvdate, item.getSy_date());
			}
		};
		mListView.setAdapter(adapter);
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ConsultInfoActivity.this.finish();
			}
		});
	}
	
	public void loadData(){
		String data = mCache.getAsString("Edu_Data");
		List<Education> edus = new ArrayList<Education>();
		edus = JsonParseUtils.parseJsonEdu(data);
		lists.addAll(edus);
		List<Education> itemEdus = new ArrayList<Education>();
		for (int i = 0; i < lists.size(); i++) {
			if(lists.get(i).getSy_type() == type){
				itemEdus.add(lists.get(i));
			}
		}
		itemLists.addAll(itemEdus);
		mHandle.sendEmptyMessage(0x0009);
	}
}
