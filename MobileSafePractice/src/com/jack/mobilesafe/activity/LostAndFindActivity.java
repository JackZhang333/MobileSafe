package com.jack.mobilesafe.activity;

import com.jack.mobliesafe.utils.SharedPreferencesUtils;
import com.jack.mobliesafepractice.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LostAndFindActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lostandfind_activity);
		//判断是否设置过向导
		if(!SharedPreferencesUtils.getBoolean(this, "config", false)){
			
			startActivity(new Intent(this,Guide1Activity.class));
		}else{
			//已经设置过向导
		}
	}
}
