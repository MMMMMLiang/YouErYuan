package com.wzt.sun.infanteducation;

import com.wzt.sun.infanteducation.fragment.InformmFragment;
import com.wzt.sun.infanteducation.fragment.InteractionFragment;
import com.wzt.sun.infanteducation.fragment.KindergartenFragment;
import com.wzt.sun.infanteducation.fragment.MeFragment;
import com.wzt.sun.infanteducation.fragment.ParadiseFragment;
import com.wzt.sun.infanteducation.netstate.NetworkStateReceiver;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends FragmentActivity {
	// 第一次点击返回键的时间
	private long firstTime;
	// fragment管理类
	private FragmentManager fm;
	// 五个Fragment界面
	private Fragment[] fragments = new Fragment[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 动画效果淡入淡出
		overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
		initView();

	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		KindergartenFragment kindergartendFragment = new KindergartenFragment();
		fragments[0] = kindergartendFragment;
		ft.add(R.id.linear_container, kindergartendFragment);
		ft.commit();

	}

	// radiogroup点击事件
	public void rbClick(View view) {
		switch (view.getId()) {
		case R.id.bottom_rb_kindergarten:
			// 幼儿园
			showFragment(0);
			hideOtherFragment(0);
			break;
		case R.id.bottom_rb_inform:
			// 通知
			showFragment(1);
			hideOtherFragment(1);
			break;
		case R.id.bottom_rb_interaction:
			// 互动
			showFragment(2);
			hideOtherFragment(2);
			break;
		case R.id.bottom_rb_paradise:
			// 乐园
			showFragment(3);
			hideOtherFragment(3);
			break;
		// 我
		case R.id.bottom_rb_me:
			showFragment(4);
			hideOtherFragment(4);
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
			// 幼儿园
			if (fragments[0] == null) {
				fragments[0] = new KindergartenFragment();
				ft.add(R.id.linear_container, fragments[0]);
			} else {
				ft.show(fragments[0]);
			}
			ft.commit();
			break;
		case 1:
			// 通知
			if (fragments[1] == null) {
				fragments[1] = new InformmFragment();
				ft.add(R.id.linear_container, fragments[1]);
			} else {
				ft.show(fragments[1]);
			}
			ft.commit();
			break;
		case 2:
			// 互动
			if (fragments[2] == null) {
				fragments[2] = new InteractionFragment();
				ft.add(R.id.linear_container, fragments[2]);
			} else {
				ft.show(fragments[2]);
			}
			ft.commit();

			break;
		case 3:
			// 乐园
			if (fragments[3] == null) {
				fragments[3] = new ParadiseFragment();
				ft.add(R.id.linear_container, fragments[3]);
			} else {
				ft.show(fragments[3]);
			}
			ft.commit();
			break;
		case 4:
			// 我
			if (fragments[4] == null) {
				fragments[4] = new MeFragment();
				ft.add(R.id.linear_container, fragments[4]);
			} else {
				ft.show(fragments[4]);
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
	
	@Override
	public void onBackPressed() {
		long secondTime = System.currentTimeMillis();
		if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
			BaseApp.getInstance().showToast("再按一次退出程序");
			firstTime = secondTime;// 更新firstTime
			return;
		} else {
			// 两次按键小于2秒时，退出应用
			finish();
			NetworkStateReceiver.unRegisterNetworkStateReceiver(BaseApp
					.getInstance());
		}
	}
}
	
