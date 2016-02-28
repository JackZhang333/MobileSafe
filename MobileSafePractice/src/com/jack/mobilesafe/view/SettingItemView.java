package com.jack.mobilesafe.view;

import com.jack.mobliesafepractice.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingItemView extends RelativeLayout {
	
	private TextView mTitle;
	private TextView mDesc;
	private CheckBox mCheck;
	
	private String NAME_SPACE ="http://schemas.android.com/apk/res/com.jack.mobliesafepractice";
	private String title;
	private String desc_on;
	private String desc_off;

	public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView();
	}

	public SettingItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView();
		title = attrs.getAttributeValue(NAME_SPACE, "title");
//		Log.d("SettingItemView","获取的标题是："+title);
		desc_on = attrs.getAttributeValue(NAME_SPACE, "desc_on");
		desc_off = attrs.getAttributeValue(NAME_SPACE, "desc_off");
		
		setTitle(title);
	}

	public SettingItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	private void initView(){
		View child = LayoutInflater.from(getContext()).inflate(R.layout.setting_item_layout, null);
		this.addView(child);
		mTitle = (TextView) child.findViewById(R.id.tv_item_settingName);
		mDesc =(TextView) child.findViewById(R.id.tv_item_settingdesc);
		mCheck = (CheckBox) child.findViewById(R.id.cb_Switch);
		
		//默认勾选复选框
		mCheck.setChecked(true);
	}
	/**
	 * 设置标题
	 * @param title
	 */
	public void setTitle(String title){
		mTitle.setText(title);
	}
	/**
	 * 设置描述的状态
	 */
	public void setDesc(String desc){
		mDesc.setText(desc);
	}
	/**
	 * 设置复选框
	 */
	public void setCheck(boolean checked){
		mCheck.setChecked(checked);
		//设置更新的状态
		if(checked){
			setDesc(desc_on);
		}else{
			setDesc(desc_off);
		}
	}
	/**
	 * 检查复选框是否选中
	 */
	public boolean isChecked(){
		return mCheck.isChecked();
		
		
	}
}
