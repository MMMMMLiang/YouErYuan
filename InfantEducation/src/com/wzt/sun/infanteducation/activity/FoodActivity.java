package com.wzt.sun.infanteducation.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.adapter.HorizontalListViewAdapter;
import com.wzt.sun.infanteducation.bean.Food;
import com.wzt.sun.infanteducation.bean.Recipe;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;
import com.wzt.sun.infanteducation.view.CustomProgressDialog;
import com.wzt.sun.infanteducation.view.HorizontalListView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 这是今日食谱页面
 * @author sun
 *
 */
public class FoodActivity extends BaseActivity {
	private ImageView titlebar_back;
	private CustomProgressDialog progressDialog = null;
	//管理线程,保证始终只开一个线程
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private List<Food> fLists;
	private HttpUtils mHttpUtils;
	private List<Recipe> rLists;
	
	private SimpleDateFormat format;
	
	private List<Food> mrMap;
	private List<Food> amupMap;
	private List<Food> afMap;
	private List<Food> afdownMap;
	private List<Food> niMap;
	
	private HorizontalListView mrGridView;
	private HorizontalListView amupGridView;
	private HorizontalListView afGridView;
	private HorizontalListView afdownGridView;
	private HorizontalListView niGridView;
	
	private HorizontalListViewAdapter adapter1;
	private HorizontalListViewAdapter adapter2;
	private HorizontalListViewAdapter adapter3;
	private HorizontalListViewAdapter adapter4;
	private HorizontalListViewAdapter adapter5;
	
	private LinearLayout llzaocan;
	private LinearLayout llzaodian;
	private LinearLayout llwucan;
	private LinearLayout llwudian;
	private LinearLayout llwancan;
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0008) {
				if(mrMap.size() != 0){
				adapter1 = new HorizontalListViewAdapter(FoodActivity.this, mrMap);
				mrGridView.setAdapter(adapter1);
				}else {
					llzaocan.setVisibility(View.GONE);
				}
				if(amupMap.size() != 0){
					adapter2 = new HorizontalListViewAdapter(FoodActivity.this, amupMap);
					amupGridView.setAdapter(adapter2);
					}else {
						llzaodian.setVisibility(View.GONE);
					}
				if(afMap.size() != 0){
					adapter3 = new HorizontalListViewAdapter(FoodActivity.this, afMap);
					afGridView.setAdapter(adapter3);
					}else {
						llwucan.setVisibility(View.GONE);
					}
				if(afdownMap.size() != 0){
					adapter4 = new HorizontalListViewAdapter(FoodActivity.this, afdownMap);
					afdownGridView.setAdapter(adapter4);
					}else {
						llwudian.setVisibility(View.GONE);
					}
				if(niMap.size() != 0){
					adapter5 = new HorizontalListViewAdapter(FoodActivity.this, niMap);
					niGridView.setAdapter(adapter5);
					}else {
						llwancan.setVisibility(View.GONE);
					}
				stopProgressDialog();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food);
		
		initView();
		startProgressDialog();
		titlebar_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				FoodActivity.this.finish();
				
			}
		});
	}
	
	public void initView(){
		titlebar_back = (ImageView) findViewById(R.id.titlebar_btn_back);
		fLists = new ArrayList<Food>();
		rLists = new ArrayList<Recipe>();
		mrMap = new ArrayList<Food>();
		amupMap = new ArrayList<Food>();
		afMap = new ArrayList<Food>();
		afdownMap = new ArrayList<Food>();
		niMap = new ArrayList<Food>();
		mHttpUtils = new HttpUtils();
		
		mrGridView = (HorizontalListView) findViewById(R.id.food_zaocan_data);
		//mrGridView = (GridView) findViewById(R.id.food_zaocan_data);
		amupGridView = (HorizontalListView) findViewById(R.id.food_zaodian_data);
		afGridView = (HorizontalListView) findViewById(R.id.food_wucan_data);
		afdownGridView = (HorizontalListView) findViewById(R.id.food_wudian_data);
		niGridView = (HorizontalListView) findViewById(R.id.food_wancan_data);
		
		llzaocan = (LinearLayout) findViewById(R.id.food_zaocan);
		llzaodian = (LinearLayout) findViewById(R.id.food_zaodian);
		llwucan = (LinearLayout) findViewById(R.id.food_wucan);
		llwudian = (LinearLayout) findViewById(R.id.food_wudian);
		llwancan = (LinearLayout) findViewById(R.id.food_wancan);
		
		loadData();
	}
	
	public void loadData(){
		executor.execute(new Runnable() {

			@Override
			public void run() {
				// 获取食物数据
				mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETFOOD, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						String data = response.result;
						List<Food> foods = JsonParseUtils.parseJsonFood(data);
						fLists.addAll(foods);
					}
				});
				mHttpUtils.send(HttpMethod.GET, ConstansUrl.GETRECIPE, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						String data = response.result;
						List<Recipe> recipes = JsonParseUtils.parseJsonRecipe(data);
						rLists.addAll(recipes);
						load(fLists, rLists);
						mHandle.sendEmptyMessage(0x0008);
					}
				});
			}
		});
	}
	
	private void load(List<Food> foodLists, List<Recipe> recipeLists){
		List<Recipe> reLists = new ArrayList<Recipe>();
		
		// 获取当前日期
		format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()); 
		String date = format.format(new Date());
		for (int i = 0; i < recipeLists.size(); i++) {
			if(recipeLists.get(i).getRe_date().equals(date)){
				reLists.add(recipeLists.get(i));
				for (int j = 0; j < reLists.size(); j++) {
					// 早餐
					String[] mrs = getShuZu(reLists.get(j).getRe_mr());
					mrMap.addAll(addMap(mrs, foodLists));
					// 上午加餐
					String[] amups = getShuZu(reLists.get(j).getRe_amup());
					amupMap.addAll(addMap(amups, foodLists));
					// 午餐
					String[] afs = getShuZu(reLists.get(j).getRe_af());
					afMap.addAll(addMap(afs, foodLists));
					// 下午加餐
					String[] afdowns = getShuZu(reLists.get(j).getRe_afdown());
					afdownMap.addAll(addMap(afdowns, foodLists));
					// 晚餐
					String[] nis = getShuZu(reLists.get(j).getRe_ni());
					niMap.addAll(addMap(nis, foodLists));
				}
			}else {
				BaseApp.getInstance().showToast("没有数据");
			}
		}
		
	}
	
	public String[] getShuZu(String str){
		String[] strs = str.split(",");
		return strs;
	}
	
	public List<Food> addMap(String[] str, List<Food> foodLists){
		String mrsStr = null;
		String foodStr = null;
		List<Food> mfLists = new ArrayList<Food>();
		for (int j1 = 0; j1 < str.length; j1++) {
			Food mFood = new Food();
			mrsStr = str[j1];
			for (int j2 = 0; j2 < foodLists.size(); j2++) {
				foodStr = foodLists.get(j2).getFo_id()+"";
				if(foodStr.equals(mrsStr)){
					mFood.setFo_img(foodLists.get(j2).getFo_img());
					mFood.setFo_name(foodLists.get(j2).getFo_name());
					mfLists.add(mFood);
				}
			}
		}
		return mfLists;

	}
	
	private void startProgressDialog(){
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("正在加载中...");
        }
         
        progressDialog.show();
    }
	
	private void stopProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
