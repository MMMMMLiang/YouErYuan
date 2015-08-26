package com.wzt.sun.infanteducation.adapter;

import java.util.List;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.utils.FilletTransform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 九宫格图片显示适配器
 * @author sun.ml
 *
 */
public class NoScrollGridAdapter extends BaseAdapter {
	// 上下文
	private Context mContext;
	// 图片的URL集合
	private List<String> imageUrls;

	public NoScrollGridAdapter(Context mContext, List<String> imageUrls) {
		super();
		this.mContext = mContext;
		this.imageUrls = imageUrls;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageUrls == null ? 0 : imageUrls.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imageUrls.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null && imageUrls.size() != 0){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.no_scroll_gridview_item, parent, false);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.ns_gridview_iv);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Picasso.with(mContext).load(imageUrls.get(position)).placeholder(R.drawable.message_placeholder_picture).error(R.drawable.message_placeholder_picture).into(viewHolder.imageView);
		return convertView;
	}
	
	private class ViewHolder {
		public ImageView imageView;
	}

}
