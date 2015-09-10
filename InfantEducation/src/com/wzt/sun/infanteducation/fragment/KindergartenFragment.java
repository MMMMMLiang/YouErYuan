package com.wzt.sun.infanteducation.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.AddLogBookActivity;
import com.wzt.sun.infanteducation.activity.AllPlanActivity;
import com.wzt.sun.infanteducation.activity.AllStuInformationActivity;
import com.wzt.sun.infanteducation.activity.AllTeacherInfo;
import com.wzt.sun.infanteducation.activity.BabyWorksActivity;
import com.wzt.sun.infanteducation.activity.CourseActivity;
import com.wzt.sun.infanteducation.activity.ExclusiveNewsActivity;
import com.wzt.sun.infanteducation.activity.FeedbackActivity;
import com.wzt.sun.infanteducation.activity.FoodActivity;
import com.wzt.sun.infanteducation.activity.IntroductionActivity;
import com.wzt.sun.infanteducation.activity.LaterActivity;
import com.wzt.sun.infanteducation.activity.PlanActivity;
import com.wzt.sun.infanteducation.activity.PostMessageActivity;
import com.wzt.sun.infanteducation.activity.SchoolNewsActivity;
import com.wzt.sun.infanteducation.activity.StarActivity;
import com.wzt.sun.infanteducation.activity.TeacherCommentActivity;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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

/**
 * 首页fragment
 * @author sun.ml
 *
 */
public class KindergartenFragment extends DialogFragment implements
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
	
	private SharedPreferences loginSp = null;
	private boolean isLogin = false;
	private boolean isLea = false;
	private boolean isStu = false;
	private boolean isTea = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_first_kindergarten, null);
		isLogin();
		initView(view);
		return view;
	}
	
	private void isLogin() {
		loginSp = getActivity().getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, Context.MODE_PRIVATE);
		isLogin = loginSp.getBoolean("isLogin", false);
		isLea = loginSp.getBoolean("isLeader", false);
		isTea = loginSp.getBoolean("isTeacher", false);
		isStu = loginSp.getBoolean("isParent", false);
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
				chooseLists(), R.layout.kgf_girdview_item,
				new String[] { "itemImage", "itemText" }, new int[] {
						R.id.kgf_gv_itemImage, R.id.kgf_gv_itemText });
		mGridView.setAdapter(smAdapter);
		mGridView.setOnItemClickListener(this);
	}
	
	private ArrayList<HashMap<String, Object>> chooseLists(){
		if(isLea){
			return ConstantsConfig.loadId3Lists();
		}else if (isTea) {
			return ConstantsConfig.loadId2Lists();
		}
		return ConstantsConfig.loadId1Lists();
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
		//BaseApp.getInstance().showToast("你点击了" + position);
		if(!isLogin){
			dialogShow();
		}else{
			if(isLea){
				//是园长
				//判断登陆状态 

				Intent mIntent = new Intent();
				/*"乐园风采", "宝贝作品", "教师信息", "幼儿信息", "明星宝贝", "今日食谱", "信息发布", "教学计划", "家长信"*/
				switch (position) {
				case 0:
					// 乐园风采
					mIntent.setClass(this.getActivity(), SchoolNewsActivity.class);
					startActivity(mIntent);
					break;
				case 1:
					// 宝贝作品
					mIntent.setClass(this.getActivity(), BabyWorksActivity.class);
					startActivity(mIntent);
					break;
				case 2:
					// 教师信息
					mIntent.setClass(this.getActivity(), AllTeacherInfo.class);
					startActivity(mIntent);
					break;
				case 3:
					// 幼儿信息
					mIntent.setClass(this.getActivity(), AllStuInformationActivity.class);
					startActivity(mIntent);
					break;
				case 4:
					// 明星宝贝
					mIntent.setClass(this.getActivity(), StarActivity.class);
					startActivity(mIntent);
					break;
				case 5:
					// 今日食谱
					mIntent.setClass(getActivity(), FoodActivity.class);
					startActivity(mIntent);
					break;
				case 6:
					// 信息发布
					mIntent.setClass(this.getActivity(), PostMessageActivity.class);
					mIntent.putExtra("ISPOST", 1);
					startActivity(mIntent);
					break;
				case 7:
					// 教学计划
					mIntent.setClass(this.getActivity(), AllPlanActivity.class);
					startActivity(mIntent);
					break;
				case 8:
					// 家长信
					mIntent.setClass(this.getActivity(), LaterActivity.class);
					startActivity(mIntent);
					break;

				default:
					break;
				}


			}else if (isTea) {
				// 是教师
				//判断登陆状态 

				/*"校园简介", "乐园风采", "宝宝作品", "老师点评", "明星宝贝", "今日食谱", "花名册", "教学计划", "考勤请假"*/
				Intent mIntent = new Intent();
				switch (position) {
				case 0:
					// 校园简介
					mIntent.setClass(this.getActivity(), IntroductionActivity.class);
					startActivity(mIntent);
					break;
				case 1:
					// 乐园风采
					mIntent.setClass(this.getActivity(), SchoolNewsActivity.class);
					startActivity(mIntent);
					break;
				case 2:
					// 宝宝作品
					mIntent.setClass(this.getActivity(), BabyWorksActivity.class);
					startActivity(mIntent);
					break;
				case 3:
					// 老师点评
					mIntent.setClass(this.getActivity(), TeacherCommentActivity.class);
					startActivity(mIntent);
					break;
				case 4:
					// 明星宝宝
					mIntent.setClass(this.getActivity(), StarActivity.class);
					startActivity(mIntent);
					break;
				case 5:
					// 今日食谱
					mIntent.setClass(getActivity(), FoodActivity.class);
					startActivity(mIntent);
					break;
				case 6:
					// 花名册
					mIntent.setClass(this.getActivity(), AllStuInformationActivity.class);
					startActivity(mIntent);
					break;
				case 7:
					// 教学计划
					mIntent.setClass(this.getActivity(), CourseActivity.class);
					mIntent.putExtra("ALLCOURSE", 2);
					startActivity(mIntent);
					break;
				case 8:
					// 考勤请假
					mIntent.setClass(this.getActivity(), AddLogBookActivity.class);
					startActivity(mIntent);
					break;

				default:
					break;
				}


			}else if (isStu) {
				//是家长
				//判断登陆状态 

				/*"校园简介", "乐园风采", "宝宝作品", "老师点评", "明星宝贝", "今日食谱", "课时安排", "活动专区", "园长信箱"*/
				Intent mIntent = new Intent();
				switch (position) {
				case 0:
					// 校园简介
					mIntent.setClass(this.getActivity(), IntroductionActivity.class);
					startActivity(mIntent);
					break;
				case 1:
					// 乐园风采
					mIntent.setClass(this.getActivity(), SchoolNewsActivity.class);
					startActivity(mIntent);
					break;
				case 2:
					// 宝宝作品
					mIntent.setClass(this.getActivity(), BabyWorksActivity.class);
					startActivity(mIntent);
					break;
				case 3:
					// 老师点评
					mIntent.setClass(this.getActivity(), TeacherCommentActivity.class);
					startActivity(mIntent);
					break;
				case 4:
					// 明星宝宝
					mIntent.setClass(this.getActivity(), StarActivity.class);
					startActivity(mIntent);
					break;
				case 5:
					// 今日食谱
					mIntent.setClass(getActivity(), FoodActivity.class);
					startActivity(mIntent);
					break;
				case 6:
					// 课时安排
					mIntent.setClass(this.getActivity(), CourseActivity.class);
					startActivity(mIntent);
					break;
				case 7:
					// 活动专区
					mIntent.setClass(this.getActivity(), ExclusiveNewsActivity.class);
					startActivity(mIntent);
					break;
				case 8:
					// 园长信箱
					mIntent.setClass(this.getActivity(), FeedbackActivity.class);
					mIntent.putExtra("BIAOSHI", 2);
					startActivity(mIntent);
					break;

				default:
					break;
				}
			}
		}
	}

	private void dialogShow() {
		DialogFragment newFragment = new MyAlertDialogFragment();
		newFragment.show(getFragmentManager(), "dialog");
	}

}
