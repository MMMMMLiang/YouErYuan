package com.wzt.sun.infanteducation.netstate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Locale;

/**
 * 
 * 
 * @项目名称：ZwUtils
 * @类名称：NetWorkUtil
 * @类描述： 是检测网络的一个工具包
 * @创建人：张唯
 * @修改人：
 * @修改备注：
 * @version
 * 
 */
public class NetWorkUtil {
	public static enum NetType {
		wifi, CMNET, CMWAP, noneNet, GWAP_3, GNET_3, UNIWAP, UNINET, CTWAP, CTNET
	}
	/**
	 * 网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (info != null) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断WIFI网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断MOBILE网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 获取当前网络连接的类型信息
	 * 
	 * @param context
	 * @return
	 */
	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

	/**
	 * 
	 * @方法名: getAPNType
	 * @说 明: 获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap 网络3：net网络
	 * @参 数: @param context
	 * @参 数: @return
	 * @return netType 返回类型
	 * @throws
	 */
	public static NetType getAPNType(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return NetType.noneNet;
		}
		int nType = networkInfo.getType();

		if (nType == ConnectivityManager.TYPE_MOBILE) {
			// 中国移动
			if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals(APNNet.CMNET)) {
				return NetType.CMNET;
			} else if (networkInfo.getExtraInfo().toUpperCase(Locale.getDefault()).equals(APNNet.CMWAP)) {
				return NetType.CMWAP;
				// 中国联通
			} else if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals(APNNet.GNET_3)) {
				return NetType.GNET_3;
			} else if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals(APNNet.GWAP_3)) {
				return NetType.GWAP_3;
			} else if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals(APNNet.UNIWAP)) {
				return NetType.UNIWAP;
			} else if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals(APNNet.UNINET)) {
				return NetType.UNINET;
				// 中国联通
			} else if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals(APNNet.CTNET)) {
				return NetType.CTNET;
			} else if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals(APNNet.CTWAP)) {
				return NetType.CTNET;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			return NetType.wifi;
		}
		return NetType.noneNet;
	}

	public static class APNNet {
		/**
		 * 　 中国移动cmwap 　　
		 */
		public static String CMWAP = "cmwap";
		/**
		 * 中国移动cmnet
		 */
		public static String CMNET = "cmnet";
		/**
		 * 3G wap 中国联通3gwap APN
		 */
		public static String GWAP_3 = "3gwap";

		/**
		 * 3G net 中国联通3gnet APN 　　
		 */
		public static String GNET_3 = "3gnet";
		/**
		 * uni wap 中国联通uni wap APN 　　
		 */
		public static String UNIWAP = "uniwap";
		/**
		 * uni net 中国联通uni net APN 　　
		 */
		public static String UNINET = "uninet";

		/**
		 * 中国电信 net APN 　　
		 */
		public static String CTNET = "ctnet";
		/**
		 * 中国电信 net APN 　　
		 */
		public static String CTWAP = "ctwap";
	}
}
