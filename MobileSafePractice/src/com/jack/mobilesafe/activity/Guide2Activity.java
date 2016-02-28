package com.jack.mobilesafe.activity;

import com.jack.mobliesafepractice.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Guide2Activity extends Activity {
	Button btn_next;
	Button btn_previous;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide2_activity);
		btn_next = (Button) findViewById(R.id.btn_next2);
		btn_previous = (Button) findViewById(R.id.btn_previous2);
		
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),Guide3Activity.class));
			}
		});
		btn_previous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),Guide1Activity.class));
			}
		});
	}
}
