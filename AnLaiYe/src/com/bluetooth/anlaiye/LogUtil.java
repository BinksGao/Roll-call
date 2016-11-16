package com.bluetooth.anlaiye;

import android.util.Log;

/**
 * @author 高小黑
 *
 * 2016年5月14日下午8:31:50
 */
public class LogUtil {

	public static final String TAG = "LogUtil";

	private static final boolean debug = true; 

	// ��־�ȼ�
	public static final int log_level1 = 1;
	public static final int log_level2 = 2;
	public static final int log_level3 = 3;
	public static final int log_level4 = 4;

	public static final int LEVEL_1 = 1; 
	public static final int LEVEL_2 = 2; 
	public static final int LEVEL_3 = 3; 

	public static final int log_level_default = log_level4; 

	private static final int[] log_level = { 1, 2, 3, 4 }; 


	private static final String[] log_tag = { "SymbolPopupWindow.TAG",
			"SymbolAdapter.TAG", "KeyboardAnimation.TAG" };

	public static final int log_level_disable = 0xff;
	private static final int LOGE = 0;
	private static final int LOGI = 1;

	public static void i(String msg) {
		i(TAG, msg, log_level_default);
	}

	public static void i(String msg, int level) {
		i(TAG, msg, level);
	}

	public static void i(String TAG, String msg, int level) {
		log(TAG, msg, "", level, LOGI);
	}

	public static void e(String error) {
		log(TAG, "", error, log_level1, LOGE);
	}


	public static void e(String TAG, String msg, String error) {
		log(TAG, msg, error, log_level1, LOGE);
	}

	public static void log(String TAG, String msg, String error, int level,
			int type) {
		if (debug) {
			for (int i = 0; i < log_level.length; i++) {
				if (level == log_level[i]) {
					String out_tag = TAG;
					for (int j = 0; j < log_tag.length; j++) {
						if (TAG.equals(log_tag[j])) {
							out_tag = LogUtil.TAG;
							msg = TAG + " " + msg;
							break;

						}
					}
					if (type == LOGE) {
						Log.e(out_tag, msg);
						Log.e(out_tag, error);
					} else {
						Log.i(out_tag, msg);
					}
					break;
				}
			}
		}

	}

	public static final boolean DEGUG_MODE = true; 
	public static final String[] fileter = new String[] {};

	public static void e(String tag, String info) {
		if (!DEGUG_MODE) {
			return;
		} else {
			if (fileter.length <= 0) {
				Log.e(tag, info);
			} else {
				for (int i = 0; i < fileter.length; i++) {
					if (fileter[i].equalsIgnoreCase(tag)) {
						Log.e(tag, info);
					}
				}
			}
		}
	}

	public static void i(String tag, String info) {
		if (!DEGUG_MODE) {
			return;
		} else {
			if (fileter.length <= 0) {
				Log.i(tag, info);
			} else {
				for (int i = 0; i < fileter.length; i++) {
					if (fileter[i].equalsIgnoreCase(tag)) {
						Log.i(tag, info);
					}
				}
			}
		}
	}

	public static void d(String tag, String info) {
		if (!DEGUG_MODE) {
			return;
		} else {
			if (fileter.length <= 0) {
				Log.d(tag, info);
			} else {
				for (int i = 0; i < fileter.length; i++) {
					if (fileter[i].equalsIgnoreCase(tag)) {
						Log.d(tag, info);
					}
				}
			}
		}
	}
}

