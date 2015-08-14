package com.wzt.sun.infanteducation.activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.ACache;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * 教育教学页面
 * @author sun
 *
 */
public class ConsultActivity extends BaseActivity {
	
	private ImageView iv;
	private GridView gv;
	
	private HttpUtils mHttpUtils;
	private ACache mCache;
	
	//管理线程,保证始终只开一个线程
	private ExecutorService executor = Executors.newSingleThreadExecutor();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consult);
		
		initView();
		loadData();
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				ConsultActivity.this.finish();
			}
		});
	}
	
	public void initView(){
		mCache = ACache.get(this);
		iv = (ImageView) findViewById(R.id.titlebar_consult_btn_back);
		gv = (GridView) findViewById(R.id.consult_edu_gridview);
		mHttpUtils = new HttpUtils();
		
		gv.setAdapter(new MyGVAdapter(this));
		
		gv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(ConsultActivity.this, ConsultInfoActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putInt("EDU_ITEM", position+1);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
			
		});
	}
	
	public void loadData(){
		executor.execute(new Runnable() {
			@Override
			public void run() {
				mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETEDUCATION, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						String data = response.result;
						if(data.trim().isEmpty()){
							return;
						}else {
							mCache.put("Edu_Data", data, 1*ACache.TIME_HOUR);
						}
					}
				});
				
			}
		});
	}

//自定义适配器 
class MyGVAdapter extends BaseAdapter{ 
	//上下文对象 
	private Context context; 
	//图片数组 
	private Integer[] imgs = { 
			R.drawable.education_game, R.drawable.education_tiyu, 
			R.drawable.education_xingqu, R.drawable.education_yuyan 
	}; 
	MyGVAdapter(Context context){ 
		this.context = context; 
	} 
	public int getCount() { 
		return imgs.length; 
	} 
	
	public Object getItem(int item) { 
		return item; 
	} 
	
	public long getItemId(int id) { 
		return id; 
	} 
	
	//创建View方法 
	public View getView(int position, View convertView, ViewGroup parent) { 
		ViewHolder viewHolder = null;
		if (convertView == null) {  
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.activity_consult_item, null);
			viewHolder.img = (ImageView) convertView.findViewById(R.id.consult_item_icon);
			convertView.setTag(viewHolder);
		}  
		else 
			viewHolder = (ViewHolder) convertView.getTag();
			Picasso.with(ConsultActivity.this).load(imgs[position]).resize(240, 200).centerCrop().into(viewHolder.img);
		
		return convertView; 
	}
	class ViewHolder {
		public ImageView img;
	}
	
} 
}
