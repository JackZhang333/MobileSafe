package com.jack.mobliesafe.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	
	public static void show(Context ctx,String content){
		
		Toast.makeText(ctx, content, Toast.LENGTH_SHORT).show();
	}
}
