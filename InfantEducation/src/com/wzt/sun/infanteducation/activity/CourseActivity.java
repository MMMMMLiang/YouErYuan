package com.wzt.sun.infanteducation.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Syllabus;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;
import com.wzt.sun.infanteducation.view.CalendarView;
import com.wzt.sun.infanteducation.view.CalendarView.OnItemClickListener;
import com.wzt.sun.infanteducation.view.CustomProgressDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 这是课程安排界面
 * @author sun
 *
 */
public class CourseActivity extends BaseActivity implements OnItemClickListener{
	
	//自定义日历控件
	private CalendarView mCalView;
	private TextView calendarCenter;
	private SimpleDateFormat format;
	private Calendar c;
	private int mDay;
	
	private ImageView iv;
	
	private CustomProgressDialog progressDialog = null;
	private List<Syllabus> lists;
	private HttpUtils mHttpUtils;
	
	private String url = null;
	private String classId = "100000";
	private List<String> classLists;
	
	private TextView tv;
	private LinearLayout ll;
	private TextView tv_am;
	private TextView tv_pm;
	
	private String dinjiDateAm;
	private String dinjiDatePm;
	
	private String todayClassInfo;
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
	
	//管理线程,保证始终只开一个线程
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x0002:
				mCalView.invalidate();
				stopProgressDialog();
				break;
			case 0x003:
				tv_am.setText(dinjiDateAm);
				tv_pm.setText(dinjiDatePm);
				tv.setVisibility(View.INVISIBLE);
				ll.setVisibility(View.VISIBLE);
				ll.invalidate();
				break;
			case 0x004:
				tv.setVisibility(View.VISIBLE);
				ll.setVisibility(View.INVISIBLE);
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		initView();
		loadData();
	}
	
	public void initView() {
		iv = (ImageView) findViewById(R.id.titlebar_course_btn_back);
		
		format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		lists = new ArrayList<Syllabus>();
		mHttpUtils = new HttpUtils();
		//获取日历控件对象
		mCalView = (CalendarView) findViewById(R.id.course_calendarview);
		mCalView.setSelected(false);
		calendarCenter = (TextView)findViewById(R.id.course_datetitle_center);
		tv = (TextView) findViewById(R.id.today_syll_text);
		ll = (LinearLayout) findViewById(R.id.today_syll_ll);
		tv.setVisibility(View.INVISIBLE);
		ll.setVisibility(View.INVISIBLE);
		tv_am = (TextView) findViewById(R.id.today_syll_am);
		tv_pm = (TextView) findViewById(R.id.today_syll_pm);

		classLists = new ArrayList<String>();
		
		//创建pregressdialog
		startProgressDialog();
		//获取当前月份的日期号码
		c = Calendar.getInstance();
		//获取当前月份的日期号码 
		mDay = c.get(Calendar.DAY_OF_MONTH);
		try {
			//设置日历日期
			Date date = format.parse("2015-01-01");
			mCalView.setCalendarData(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//获取日历中年月 ya[0]为年，ya[1]为月（格式大家可以自行在日历控件中改）
		String[] ya = mCalView.getYearAndmonth().split("-");
		calendarCenter.setText(ya[0]+"年"+ya[1]+"月"+mDay+"日");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
		todayClassInfo = sdf.format(curDate);       
		mCalView.setOnItemClickListener(this);
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				CourseActivity.this.finish();
			}
		});
	}
	
	/**
	 * 加载数据
	 */
	public void loadData() {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				url = ConstansUrl.SYLLURL + classId;
				mHttpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						// TODO Auto-generated method stub
						String data = response.result;
						List<Syllabus> syllabus = JsonParseUtils.parseJsonSyllabus(data);
						lists.addAll(syllabus);
						for (int i = 0; i < lists.size(); i++) {
							classLists.add(lists.get(i).getSy_date());
						}
						mCalView.setList(classLists);
						mHandle.sendEmptyMessage(0x0002);
						todayInfo(todayClassInfo);
					}
				});
			}
		});
	}
	
	public void ivClick(View view){
		switch (view.getId()) {
		//点击左ivbutton
		case R.id.course_datetitle_left:
			//点击上一月 同样返回年月 
			String leftYearAndmonth = mCalView.clickLeftMonth(); 
			String[] ya = leftYearAndmonth.split("-"); 
			calendarCenter.setText(ya[0]+"年"+ya[1]+"月"+mDay+"日");
			break;
		//点击右ivbutton
		case R.id.course_datetitle_right:
			//点击下一月
			String rightYearAndmonth = mCalView.clickRightMonth();
			String[] yaNext = rightYearAndmonth.split("-"); 
			calendarCenter.setText(yaNext[0]+"年"+yaNext[1]+"月"+mDay+"日");
			break;

		default:
			break;
		}
	}

	@Override
	public void OnItemClick(Date selectedStartDate, Date selectedEndDate, Date downDate) {
		// TODO Auto-generated method stub

		if(mCalView.isSelectMore()){
			Toast.makeText(getApplicationContext(), format.format(selectedStartDate)+"到"+format.format(selectedEndDate), Toast.LENGTH_SHORT).show();
		}else{
			//Toast.makeText(getApplicationContext(), format.format(downDate), Toast.LENGTH_SHORT).show();
			String date = sdf.format(downDate);
			String[] rq = date.split("-"); 
			calendarCenter.setText(rq[0]+"年"+Integer.parseInt(rq[1])+"月"+Integer.parseInt(rq[2])+"日");
			for (int i = 0; i < classLists.size(); i++) {
				String classDate = classLists.get(i);
				if(date.equals(classDate)){
					dinjiDateAm = lists.get(i).getSy_am();
					dinjiDatePm = lists.get(i).getSy_pm();
					mHandle.sendEmptyMessage(0x003);
					return;
				}else {
					mHandle.sendEmptyMessage(0x004);
				}
			}
		}
	
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
	
	private void todayInfo(String date){
		for (int i = 0; i < classLists.size(); i++) {
			String classDate = classLists.get(i);
			if(date.equals(classDate)){
				dinjiDateAm = lists.get(i).getSy_am();
				dinjiDatePm = lists.get(i).getSy_pm();
				mHandle.sendEmptyMessage(0x003);
				return;
			}else {
				mHandle.sendEmptyMessage(0x004);
			}
		}
	}

}
