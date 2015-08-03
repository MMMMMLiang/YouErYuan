package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.List;

import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class WelcomeActivity extends Activity implements OnPageChangeListener{
	
	//引导页面的图片显示
	private ViewPager mViewPager;
	private LinearLayout viewPager_dots;
	
	//定义图片数据源与适配器
	private List<View> lists;
	private MyPagerAdapter adapter;
	
	private SharedPreferences prefs = null;
	private boolean isFirst = false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		isFirstRun();
		initView();
	}
	
	private void isFirstRun() {
		prefs = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		// 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
		isFirst = prefs.getBoolean("isFirstIn", true);
		// 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
		if (!isFirst) {
			Intent intent = new Intent(this, GuideActivity.class);
			startActivity(intent);
			finish();
		} else {
			Editor editor = prefs.edit();
			// 存入数据
			editor.putBoolean("isFirstIn", false);
			// 提交修改
			editor.commit();
		}
	}

	public void initView() {
		mViewPager = (ViewPager) findViewById(R.id.viewPager_welcome);
		viewPager_dots = (LinearLayout) findViewById(R.id.viewPager_dots);
		
		addView();
		adapter = new MyPagerAdapter();
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);
		
	}
	
	private void addView() {
		lists = new ArrayList<View>();
		//图片数组
		int[] ivIcons = new int[]{R.layout.welcome_tv1, R.layout.welcome_tv2, R.layout.welcome_tv3};
		//LayoutInflater inflater = getLayoutInflater();
		// 动态加载视图
		for (int i = 0; i < ivIcons.length; i++) {
			LayoutInflater flater = LayoutInflater.from(this);
			//ImageView iv = new ImageView(this);
			View view = flater.inflate(ivIcons[i], null);
			// 设置属性
			//view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					//LayoutParams.WRAP_CONTENT));
			//iv.setImageResource(ivIcons[i]);
			//iv.setScaleType(ScaleType.FIT_XY);
			// 放入容器
			lists.add(view);
		}

		// 初始化底部小圆点(数量和图片数量一致)
		for (int i = 0; i < ivIcons.length; i++) {
			// 构建ImageView,放dot的图片
			ImageView ivDot = new ImageView(this);
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
			viewPager_dots.addView(ivDot);
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
			return lists.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO 自动生成的方法存根
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO 自动生成的方法存根
			container.addView(lists.get(position));
			return lists.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO 自动生成的方法存根
			container.removeView(lists.get(position));
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
		for (int i = 0; i < viewPager_dots.getChildCount(); i++) {
			ImageView iv = (ImageView) viewPager_dots.getChildAt(i);
			if (i == position) {
				iv.setImageResource(R.drawable.page_indicator_focused);
			} else {
				iv.setImageResource(R.drawable.page_indicator_unfocused);
			}
		}
	}
	
	public void welcomeClick(View view){
		switch (view.getId()) {
		case R.id.welcome_btn:
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}
