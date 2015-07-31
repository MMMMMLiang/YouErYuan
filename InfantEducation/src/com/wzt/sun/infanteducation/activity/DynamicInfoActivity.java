package com.wzt.sun.infanteducation.activity;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.fragment.LeaveFragment;
import com.wzt.sun.infanteducation.fragment.LogBookFragment;
import com.wzt.sun.infanteducation.fragment.StudentInformationFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class DynamicInfoActivity extends FragmentActivity {
	// fragment管理类
	private FragmentManager fm;
	// fragment集合
	private Fragment[] fragments = new Fragment[3];

	private int num;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_dynamicinfo);
		Bundle bundle = getIntent().getExtras();
		num = bundle.getInt("Fragment");
		fm = getSupportFragmentManager();
		
		loadFragment();
	}

	private void loadFragment() {
		switch (num) {
		case 1:
			showFragment(0);
			hideOtherFragment(0);
			break;
		case 2:
			showFragment(1); 
			hideOtherFragment(1);
			break;
		case 3:
			showFragment(2);
			hideOtherFragment(2);
			break;

		default:
			break;
		}
	}

	/**
	 * Fragment的显示
	 * 
	 * @param index
	 */
	private void showFragment(int index) {
		FragmentTransaction ft = fm.beginTransaction();
		switch (index) {
		case 0:
			// 学生信息
			if (fragments[0] == null) {
				fragments[0] = new StudentInformationFragment();
				ft.add(R.id.dy_info, fragments[0]);
			} else {
				ft.show(fragments[0]);
			}
			ft.commit();
			break;
		case 1:
			// 请假申明
			if (fragments[1] == null) {
				fragments[1] = new LeaveFragment();
				ft.add(R.id.dy_info, fragments[1]);
			} else {
				ft.show(fragments[1]);
			}
			ft.commit();
			break;
		case 2:
			// 考勤统计
			if (fragments[2] == null) {
				fragments[2] = new LogBookFragment();
				ft.add(R.id.dy_info, fragments[2]);
			} else {
				ft.show(fragments[2]);
			}
			ft.commit();

			break;
		}
	}

	/**
	 * Fragment的隐藏
	 * 
	 * @param index
	 */
	private void hideOtherFragment(int index) {
		for (int i = 0; i < fragments.length; i++) {
			if (i != index && fragments[i] != null) {
				FragmentTransaction ft = fm.beginTransaction();
				ft.hide(fragments[i]);
				ft.commit();
			}
		}
	}

}
