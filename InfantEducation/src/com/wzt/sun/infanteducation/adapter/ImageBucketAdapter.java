package com.wzt.sun.infanteducation.adapter;

import java.util.List;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.ImageBucket;
import com.wzt.sun.infanteducation.utils.ImageDisplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageBucketAdapter extends BaseAdapter
{
	private List<ImageBucket> mDataList;
	private Context mContext;

	public ImageBucketAdapter(Context context, List<ImageBucket> dataList)
	{
		this.mContext = context;
		this.mDataList = dataList;
	}

	@Override
	public int getCount()
	{
		return mDataList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder mHolder;
		if (convertView == null)
		{
			convertView = View.inflate(mContext, R.layout.item_bucket_list,
					null);
			mHolder = new ViewHolder();
			mHolder.coverIv = (ImageView) convertView.findViewById(R.id.cover);
			mHolder.titleTv = (TextView) convertView.findViewById(R.id.title);
			mHolder.countTv = (TextView) convertView.findViewById(R.id.count);
			convertView.setTag(mHolder);
		}
		else
		{
			mHolder = (ViewHolder) convertView.getTag();
		}

		final ImageBucket item = mDataList.get(position);

		if (item.imageList != null && item.imageList.size() > 0)
		{
			String thumbPath = item.imageList.get(0).thumbnailPath;
			String sourcePath = item.imageList.get(0).sourcePath;
			ImageDisplayer.getInstance(mContext).displayBmp(mHolder.coverIv, thumbPath,
					sourcePath);
		}
		else
		{
			mHolder.coverIv.setImageBitmap(null);
		}

		mHolder.titleTv.setText(item.bucketName);
		mHolder.countTv.setText(item.count + "张");

		return convertView;
	}

	static class ViewHolder
	{
		private ImageView coverIv;
		private TextView titleTv;
		private TextView countTv;
	}

}
