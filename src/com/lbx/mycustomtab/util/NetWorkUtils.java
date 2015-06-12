package com.lbx.mycustomtab.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * You should add <strong>android.permission.ACCESS_NETWORK_STATE</strong> in manifest, to get network status
 * @author liubaoxing
 *
 */
public class NetWorkUtils {
	
	private static String LOG_TAG = "NetWorkUtils";
	
	public static final String 	NETWORK_TYPE_WIFI = "wifi";
	public static final String 	NETWORK_TYPE_4g = "4g";
	public static final String 	NETWORK_TYPE_3G = "3g";
	public static final String 	NETWORK_TYPE_2G = "2g";
	public static final String 	NETWORK_TYPE_WAP = "wap";
	public static final String 	NETWORK_TYPE_UNKNOWN = "unknown";
	public static final String 	NETWORK_TYPE_DISCONNECT = "diconnect";
	
	/**
	 * 获取网络类型
	 * @param context
	 * @return
	 */
	public static int getNetworkType(Context context){
		ConnectivityManager mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = mConnectivityManager == null ? null:mConnectivityManager.getActiveNetworkInfo();
		return networkInfo == null ? -1 : networkInfo.getType();
	}
	/**
	 * 获取网络类型的名称
	 * @param context
	 * @return
	 */
	public static String getNetworkTypeName(Context context){
		String type = NETWORK_TYPE_DISCONNECT;
		ConnectivityManager mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo;
		if(mConnectivityManager == null || (networkInfo = mConnectivityManager.getActiveNetworkInfo()) == null){
			return type;
		}
		if(networkInfo.isConnected()){
			String typeName = networkInfo.getTypeName();
			if("WIFI".equalsIgnoreCase(typeName)){
				type = NETWORK_TYPE_WIFI;
			}else if("MOBILE".equalsIgnoreCase(typeName)){
				@SuppressWarnings("deprecation")
				String proxyHost = android.net.Proxy.getDefaultHost();
				type = TextUtils.isEmpty(proxyHost)?(isFastMobileNetwork(context) ? NETWORK_TYPE_3G : NETWORK_TYPE_2G) : NETWORK_TYPE_WAP;
			}else{
				type = NETWORK_TYPE_UNKNOWN;
			}
		}
		return type;
	}
 
	/**
	 * 是否是快速的移动网络
	 * @param context
	 * @return
	 */
	private static boolean isFastMobileNetwork(Context context){
		TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		if(telephonyManager == null)return false;
		switch (telephonyManager.getNetworkType()) {
		case TelephonyManager.NETWORK_TYPE_1xRTT:
			return false;
		case TelephonyManager.NETWORK_TYPE_CDMA:
			return false;
		case TelephonyManager.NETWORK_TYPE_EDGE:
			return false;
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
			return true;
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			return true;
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
			return true;
		case TelephonyManager.NETWORK_TYPE_EHRPD:
			return true;
		case TelephonyManager.NETWORK_TYPE_GPRS:
			return false;
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			return true;
		case TelephonyManager.NETWORK_TYPE_HSPA:
			return true;
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			return true;
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return true;
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return false;
		case TelephonyManager.NETWORK_TYPE_LTE://13
			return true;
		case TelephonyManager.NETWORK_TYPE_UMTS:
			return true;
		case TelephonyManager.NETWORK_TYPE_UNKNOWN:
			return false;
		default:
			return false;
		}
	}
	/**
	 * 判断是否有网络连接
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			Log.w(LOG_TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].isAvailable()) {
						Log.d(LOG_TAG, "network is available");
						return true;
					}
				}
			}
		}
		Log.d(LOG_TAG, "network is not available");
		return false;
	}
	/**
	 * 判断网络是否为漫游
	 */
	public static boolean isNetworkRoaming(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.w(LOG_TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null
					&& info.getType() == ConnectivityManager.TYPE_MOBILE) {
				TelephonyManager tm = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				if (tm != null && tm.isNetworkRoaming()) {
					Log.d(LOG_TAG, "network is roaming");
					return true;
				} else {
					Log.d(LOG_TAG, "network is not roaming");
				}
			} else {
				Log.d(LOG_TAG, "not using mobile network");
			}
		}
		return false;
	}
	/**
	 * 判断MOBILE网络是否可用
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static boolean isMobileDataEnable(Context context) throws Exception {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean isMobileDataEnable = false;

		isMobileDataEnable = connectivityManager.getNetworkInfo(
				ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

		return isMobileDataEnable;
	}
	/**
	 * 判断wifi 是否可用
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static boolean isWifiDataEnable(Context context) throws Exception {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean isWifiDataEnable = false;
		isWifiDataEnable = connectivityManager.getNetworkInfo(
				ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		return isWifiDataEnable;
	}
}

















