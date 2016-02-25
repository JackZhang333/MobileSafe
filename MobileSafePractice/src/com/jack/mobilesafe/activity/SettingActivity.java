package com.jack.mobilesafe.activity;

import com.jack.mobilesafe.view.SettingItemView;
import com.jack.mobliesafepractice.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingActivity extends Activity {
	private SettingItemView siv_update;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);
		siv_update = (SettingItemView) findViewById(R.id.siv_update);
		siv_update.setTitle("AutoUpdate");
		initeUpdate();
	}
	private void initeUpdate(){
		sp = getSharedPreferences("settingfile", MODE_PRIVATE);
		
		boolean Checked =sp.getBoolean("update", true);
		Log.d("SettingActivity", "是否更新"+Checked);
		siv_update.setCheck(Checked);
		siv_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(siv_update.isChecked()){
					siv_update.setCheck(false);
					siv_update.setDesc("自动更新已关闭");
					//把设置信息保存到本地
					sp.edit().putBoolean("update", false).commit();
				}else{
					siv_update.setCheck(true);
					siv_update.setDesc("自动更新已打开");
					sp.edit().putBoolean("update", true).commit();
				}
			}
		});
		
	}
}
