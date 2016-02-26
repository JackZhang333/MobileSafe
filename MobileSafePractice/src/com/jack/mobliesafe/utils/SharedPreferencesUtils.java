package com.jack.mobliesafe.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
	/**
	 * 存布尔值
	 * 
	 * @param ctx
	 *            上下文
	 * @param value
	 *            存入的布尔值
	 * @param key
	 *            对应的键名
	 */
	public static void putBoolean(Context ctx, boolean value, String key) {

		SharedPreferences sp = ctx.getSharedPreferences("settingfile",
				ctx.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}

	public static void putString(Context ctx,String key,String value) {

		SharedPreferences sp = ctx.getSharedPreferences("settingfile",
				ctx.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}

	/**
	 * 取布尔值
	 * 
	 * @param ctx
	 * @param key
	 * @param defaultValue
	 * @return 取出的值
	 */

	public static boolean getBoolean(Context ctx, String key,
			boolean defaultValue) {

		SharedPreferences sp = ctx.getSharedPreferences("settingfile",
				ctx.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);

	}
	public static String getString(Context ctx, String key,
			String defaultValue) {

		SharedPreferences sp = ctx.getSharedPreferences("settingfile",
				ctx.MODE_PRIVATE);
		return sp.getString(key, defaultValue);

	}
}
