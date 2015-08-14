package com.wzt.sun.infanteducation.adapter;

import java.util.List;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HorizontalListViewAdapter extends BaseAdapter{
	private List<Food> lists;
	private Context mContext;
	private LayoutInflater mInflater;
	private int selectIndex = -1;

	public HorizontalListViewAdapter(Context context, List<Food> lists){
		this.mContext = context;
		this.lists = lists;
		mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
	}
	@Override
	public int getCount() {
		return lists.size();
	}
	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.activity_food_item, null);
			holder.mImage=(ImageView)convertView.findViewById(R.id.food_item_icon);
			holder.mName=(TextView)convertView.findViewById(R.id.food_item_name);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		if(position == selectIndex){
			convertView.setSelected(true);
		}else{
			convertView.setSelected(false);
		}
		
		holder.mName.setText(lists.get(position).getFo_name());
		Picasso.with(mContext).load(lists.get(position).getFo_img()).resize(90, 90).centerCrop().placeholder(R.drawable.message_placeholder_picture).error(R.drawable.message_placeholder_picture).into(holder.mImage);

		return convertView;
	}

	private static class ViewHolder {
		private TextView mName ;
		private ImageView mImage;
	}
	public void setSelectIndex(int i){
		selectIndex = i;
	}
}