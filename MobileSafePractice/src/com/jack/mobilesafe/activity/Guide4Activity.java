package com.jack.mobilesafe.activity;

import com.jack.mobliesafe.utils.SharedPreferencesUtils;
import com.jack.mobliesafepractice.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Guide4Activity extends Activity {
	private Button btn_next;
	private Button btn_previous;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide4_activity);
		btn_next = (Button) findViewById(R.id.btn_next4);
		btn_previous = (Button) findViewById(R.id.btn_previous4);
		
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//记录已经设置过向导
//				SharedPreferencesUtils.putBoolean(getApplicationContext(), true, "config");
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),LostAndFindActivity.class));
			}
		});
		btn_previous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),Guide3Activity.class));
			}
		});
		
	}
}
