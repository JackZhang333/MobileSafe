package com.jack.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class HomeTextView extends TextView {

	public HomeTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public HomeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public HomeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean isFocused(){
		return true;
	}
}
