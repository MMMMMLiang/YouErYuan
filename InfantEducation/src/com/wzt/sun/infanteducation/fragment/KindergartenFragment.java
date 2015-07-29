package com.wzt.sun.infanteducation.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;

public class KindergartenFragment extends Fragment implements
		OnPageChangeListener, OnItemClickListener {

	// 广告
	private ViewPager mViewPager;
	private LinearLayout ll_dos;

	// 定义图片数据源
	private List<View> listViews;
	private MyPagerAdapter pagerAdapter;

	// 加载功能模块
	private GridView mGridView;
	private SimpleAdapter smAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater
				.inflate(R.layout.fragment_first_kindergarten, null);

		initView(view);
		return view;
	}

	private void initView(View view) {
		mViewPager = (ViewPager) view.findViewById(R.id.viewPager_banner);
		ll_dos = (LinearLayout) view.findViewById(R.id.ll_dots);
		mGridView = (GridView) view.findViewById(R.id.kg_fm_gridview);
		// 取消GridView/ListView item被点击时的效果
		mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

		init();
		pagerAdapter = new MyPagerAdapter();
		mViewPager.setAdapter(pagerAdapter);
		mViewPager.setOnPageChangeListener(this);

		smAdapter = new SimpleAdapter(getActivity(),
				ConstantsConfig.loadId1Lists(), R.layout.kgf_girdview_item,
				new String[] { "itemImage", "itemText" }, new int[] {
						R.id.kgf_gv_itemImage, R.id.kgf_gv_itemText });
		mGridView.setAdapter(smAdapter);
		mGridView.setOnItemClickListener(this);
	}

	private void init() {
		listViews = new ArrayList<View>();
		// 定义实际显示图片的数据源(记录图片的资源id)
		int[] imageIcon = new int[] { R.drawable.main_banner1,
				R.drawable.main_banner2 };
		// 动态加载视图
		for (int i = 0; i < imageIcon.length; i++) {
			ImageView iv = new ImageView(getActivity());
			// 设置属性
			iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			iv.setImageResource(imageIcon[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			// 放入容器
			listViews.add(iv);

			iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					BaseApp.getInstance().showToast("点击了图片");

				}
			});

		}

		// 初始化底部小圆点(数量和图片数量一致)
		for (int i = 0; i < imageIcon.length; i++) {
			// 构建ImageView,放dot的图片
			ImageView ivDot = new ImageView(getActivity());
			// 设置属性
			ivDot.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			// 默认初始化的时候,第一个小圆点跟其他不一样
			if (i == 0) {
				ivDot.setImageResource(R.drawable.page_indicator_focused);
			} else {
				ivDot.setImageResource(R.drawable.page_indicator_unfocused);
			}
			// ScaleType.FIT_CENTER保持不变形的情况下等比例拉伸图片,结果放在容器中间
			// ScaleType.FIT_XY图片全部拉伸并且充满容器,但是可能会发生图片形变失真
			ivDot.setScaleType(ScaleType.FIT_CENTER);
			// 设置ivDot的paddingLeft,使每个圆点的间距隔开
			ivDot.setPadding(10, 0, 0, 0);
			// 放入容器
			ll_dos.addView(ivDot);
			ivDot.setTag(i);
			ivDot.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					int position = (Integer) v.getTag();
					mViewPager.setCurrentItem(position);
				}
			});
		}

	}

	/**
	 * ViewPager适配器
	 * 
	 * @author sun.ml
	 * 
	 */
	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return listViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO 自动生成的方法存根
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO 自动生成的方法存根
			container.addView(listViews.get(position));
			return listViews.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO 自动生成的方法存根
			container.removeView(listViews.get(position));
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		// 动态更改选中的小圆点
		for (int i = 0; i < ll_dos.getChildCount(); i++) {
			ImageView iv = (ImageView) ll_dos.getChildAt(i);
			if (i == position) {
				iv.setImageResource(R.drawable.page_indicator_focused);
			} else {
				iv.setImageResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		BaseApp.getInstance().showToast("你点击了"+position);
	}

}