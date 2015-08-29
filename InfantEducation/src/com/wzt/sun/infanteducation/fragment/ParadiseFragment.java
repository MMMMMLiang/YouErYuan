package com.wzt.sun.infanteducation.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.ConsultInfoActivity;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.ACache;

/**
 * 乐园fragment
 * @author Administrator
 *
 */
public class ParadiseFragment extends Fragment {
	private GridView gv;
	private HttpUtils mHttpUtils;
	private ACache mCache;
	//管理线程,保证始终只开一个线程
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_consult, null);
		initView(view);
		loadData();
		
		return view;
	}
	
	public void initView(View view){
		mCache = ACache.get(getActivity());
		gv = (GridView) view.findViewById(R.id.consult_edu_gridview);
		mHttpUtils = new HttpUtils();
		
		gv.setAdapter(new MyGVAdapters(getActivity()));
		
		gv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(getActivity(), ConsultInfoActivity.class);
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
class MyGVAdapters extends BaseAdapter{ 
	//上下文对象 
	private Context context; 
	//图片数组 
	private Integer[] imgs = { 
			R.drawable.education_game, R.drawable.education_tiyu, 
			R.drawable.education_xingqu, R.drawable.education_yuyan 
	}; 
	MyGVAdapters(Context context){ 
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
			Picasso.with(getActivity()).load(imgs[position]).resize(240, 200).centerCrop().into(viewHolder.img);
		
		return convertView; 
	}
	class ViewHolder {
		public ImageView img;
	}
	
} 

}
