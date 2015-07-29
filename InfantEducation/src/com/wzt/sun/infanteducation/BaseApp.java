package com.wzt.sun.infanteducation;

import android.app.Application;
import android.widget.Toast;

import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.lidroid.xutils.cache.MD5FileNameGenerator;
import com.wzt.sun.infanteducation.netstate.NetChangeObserver;
import com.wzt.sun.infanteducation.netstate.NetWorkUtil;
import com.wzt.sun.infanteducation.netstate.NetworkStateReceiver;
import com.wzt.sun.infanteducation.utils.FileUtils;

public class BaseApp extends Application {

	private static BaseApp app;

	public static BaseApp getInstance() {
		return app;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = this;
		checkNet();
		imgGlobalconfig();
		stopLog();

	}

	private void checkNet() {
		// 注册监听网络状态的广播
		NetworkStateReceiver.registerNetworkStateReceiver(this);
		NetworkStateReceiver.registerObserver(new NetChangeObserver() {
			@Override
			public void onConnect(NetWorkUtil.NetType type) {
				super.onConnect(type);
				// 网络连接
				switch (type) {
				case wifi:
					break;
				// 只有指定号码才能连接专网
				// 视频监测
				case UNINET:
					break;
				default:
					break;
				}
			}

			@Override
			public void onDisConnect() {
				super.onDisConnect();
			}
		});
	}

	/**
	 * 注销网络监听广播
	 */
	@SuppressWarnings("unused")
	private void unRegisterNetworkStateReceiver() {
		// NetworkStateReceiver.removeRegisterObserver();
		NetworkStateReceiver.unRegisterNetworkStateReceiver(this);

	}

	/**
	 * 图片配置
	 */
	private void imgGlobalconfig() {
		BitmapGlobalConfig config = BitmapGlobalConfig.getInstance(this,
				FileUtils.getImageCache());
		config.setDiskCacheEnabled(true);
		config.setDiskCacheSize(100 * 1024 * 1024);
		config.setDefaultCacheExpiry(3);
		config.setFileNameGenerator(new MD5FileNameGenerator());
		config.setDefaultReadTimeout(10);
		config.setThreadPoolSize(4);
	}

	/**
	 * LOG全局控制
	 */
	private void stopLog() {

	}

	public void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

}
