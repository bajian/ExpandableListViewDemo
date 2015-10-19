package com.example.administrator.expandablelistviewdemo.util;

import android.util.Log;


/**
 * ��LogCat���з�װ��ֻ�ڵ���ʱ�����־
 * 
 * @author Sogrey
 * 
 */
public class LogWrapper {
	private static boolean DEBUG = true;

	/** info */
	public static void i(String tag, String msg) {
		if (DEBUG)
			Log.i(tag, msg);
	}

	/** debug */
	public static void d(String tag, String msg) {
		if (DEBUG)
			Log.d(tag, msg);
	}

	/** error */
	public static void e(String tag, String msg) {
		if (DEBUG)
			Log.e(tag, msg);
	}

	/** warm */
	public static void w(String tag, String msg) {
		if (DEBUG)
			Log.w(tag, msg);
	}

	/** verbose */
	public static void v(String tag, String msg) {
		if (DEBUG)
			Log.v(tag, msg);
	}
}
