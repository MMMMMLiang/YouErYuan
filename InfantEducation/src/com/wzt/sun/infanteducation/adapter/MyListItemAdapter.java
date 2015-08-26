package com.wzt.sun.infanteducation.adapter;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.ImagePagerActivity;
import com.wzt.sun.infanteducation.bean.ItemEntity;
import com.wzt.sun.infanteducation.view.NoScrollGridView;
import com.wzt.sun.infanteducation.view.PraiseView;
import com.wzt.sun.infanteducation.view.PraiseView.OnPraisCheckedListener;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListItemAdapter extends BaseAdapter implements OnPraisCheckedListener{
	private Context mContext;
	private List<ItemEntity> items;
	private int index = 0;
	private ViewHolder viewHolder = null;

	public MyListItemAdapter(Context mContext, List<ItemEntity> items) {
		super();
		this.mContext = mContext;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items == null ? 0 : items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//ViewHolder viewHolder = null;
		if(convertView == null && items.size() != 0){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.fragment_first_interaction_item, parent, false);
			viewHolder.iv_head = (ImageView) convertView.findViewById(R.id.ffii_iv_head);
			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.ffii_tv_name);
			viewHolder.tv_date = (TextView) convertView.findViewById(R.id.ffii_tv_date);
			viewHolder.tv_content = (TextView) convertView.findViewById(R.id.ffii_tv_content);
			viewHolder.ns_gridview = (NoScrollGridView) convertView.findViewById(R.id.ffii_tv_gridview);
			viewHolder.tv_zan = (TextView) convertView.findViewById(R.id.ffii_tv_zan);
			viewHolder.tv_num = (TextView) convertView.findViewById(R.id.ffii_tv_num);
			viewHolder.pv_zan = (PraiseView) convertView.findViewById(R.id.praise_view_view);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Picasso.with(mContext).load(items.get(position).getTh_image()).resize(65, 65).centerCrop().placeholder(R.drawable.message_placeholder_picture).error(R.drawable.message_placeholder_picture).into(viewHolder.iv_head);
		viewHolder.tv_name.setText(items.get(position).getTh_name());
		viewHolder.tv_date.setText(items.get(position).getTh_date());
		viewHolder.tv_content.setText(items.get(position).getTh_content());
		viewHolder.tv_zan.setText(items.get(position).getTh_zan()+"");
		viewHolder.tv_num.setText(items.get(position).getTh_num()+"");
		index = items.get(position).getTh_zan();
		final ArrayList<String> imageUrls = getList(items.get(position).getTh_accessory());
		if (imageUrls == null || imageUrls.size() == 0) { // 没有图片资源就隐藏GridView
			viewHolder.ns_gridview.setVisibility(View.GONE);
		} else {
			viewHolder.ns_gridview.setAdapter(new NoScrollGridAdapter(mContext, imageUrls));
		}
		viewHolder.ns_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				imageBrower(position, imageUrls);
			}
		});
		viewHolder.pv_zan.setOnPraisCheckedListener(this);
		return convertView;
	}
	
	private class ViewHolder {
		public ImageView iv_head;
		public TextView tv_name;
		public TextView tv_date;
		public TextView tv_content;
		public NoScrollGridView ns_gridview;
		public TextView tv_zan;
		public TextView tv_num;
		public PraiseView pv_zan;
	}
	
	private ArrayList<String> getList(String str){
		ArrayList<String> lists = new ArrayList<String>();
		String[] strs = str.split(",");
		for (int i = 0; i < strs.length; i++) {
			lists.add(strs[i]);
		}
		return lists;
	}
	
	/**
	 * 打开图片查看器
	 * 
	 * @param position
	 * @param urls2
	 */
	protected void imageBrower(int position, ArrayList<String> urls2) {
		Intent intent = new Intent(mContext, ImagePagerActivity.class);
		// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		mContext.startActivity(intent);
	}

	/*@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		ViewHolder mHolder = new ViewHolder();
		mHolder.tv_zan = (TextView) view.findViewById(R.id.ffii_tv_zan);
		if(isCheck){
			isCheck = false;
			String str = index-1+"";
			mHolder.tv_zan.setText(index-1+"");
			Log.i("STR", str);
		}else {
			isCheck = true;
			String str = index+1+"";
			mHolder.tv_zan.setText(index+1+"");
			Log.i("STR", str);
		}
	}*/

	@Override
	public void onPraisChecked(boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
			isChecked = false;
			String str = index+1+"";
			index += 1;
			viewHolder.tv_zan.setText(str);
			//Log.i("STR", str);
		}else {
			isChecked = true;
			String str = index-1+"";
			index -= 1;
			viewHolder.tv_zan.setText(str);
			//Log.i("STR", str);
		}
	}
	
}
