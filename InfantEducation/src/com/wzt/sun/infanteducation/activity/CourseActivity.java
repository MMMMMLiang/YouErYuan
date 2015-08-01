package com.wzt.sun.infanteducation.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.view.CalendarView;
import com.wzt.sun.infanteducation.view.CalendarView.OnItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		initView();
	}
	
	public void initView() {
		format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

		//获取日历控件对象
		mCalView = (CalendarView) findViewById(R.id.course_calendarview);
		mCalView.setSelected(false);
		calendarCenter = (TextView)findViewById(R.id.course_datetitle_center);

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
		mCalView.setOnItemClickListener(this);
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
			Toast.makeText(getApplicationContext(), format.format(downDate), Toast.LENGTH_SHORT).show();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			String date = sdf.format(downDate);
			String[] rq = date.split("-"); 
			calendarCenter.setText(rq[0]+"年"+Integer.parseInt(rq[1])+"月"+Integer.parseInt(rq[2])+"日");
		}
	
	}

}
