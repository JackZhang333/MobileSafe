package com.jack.mobilesafe.activity;

import com.jack.mobliesafe.utils.MD5Utils;
import com.jack.mobliesafe.utils.SharedPreferencesUtils;
import com.jack.mobliesafe.utils.ToastUtils;
import com.jack.mobliesafepractice.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends Activity {
	
	private GridView mGridView;
	private HomeAdapter adapter;
	/**
	 * 初始化网格布局的数据源
	 */
	private String[] mHomeItemNames = new String[]{
			"手机防盗", "通讯卫士", "软件管理",
			"进程管理", "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心"
	};
	
	private int[] mHomeIcons = new int[]{
			R.drawable.home_safe,
			R.drawable.home_callmsgsafe, R.drawable.home_apps,
			R.drawable.home_taskmanager, R.drawable.home_netmanager,
			R.drawable.home_trojan, R.drawable.home_sysoptimize,
			R.drawable.home_tools, R.drawable.home_settings
	};
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		mGridView = (GridView) findViewById(R.id.gv_home);
		adapter = new HomeAdapter();
		mGridView.setAdapter(adapter);
		
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(position){
				//进入手机防盗
				case 0:
					//判断是否要设置密码，或者是输入密码
					if(!pwdBeSetted()){
						//显示密码输入框
						showSetArlertDialog();
					}else{
						//显示密码设置框
						showInputAlertDialog();
					}
					break;
				//进入设置中心
				case 8:
					Log.d("HomeActivity", "点击了第九个按钮！");
					Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
	}
	/**
	 * 展示密码输入界面
	 */
	protected void showInputAlertDialog() {
		// TODO Auto-generated method stub
		Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog =builder.create();
		
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pwdinput_dialog_layout, null);
		dialog.setView(view, 0, 0, 0, 0);
		
		final EditText et_pwd =(EditText) view.findViewById(R.id.input_et_pwd);
		
		Button bt_ok = (Button) view.findViewById(R.id.bt_ok);
		Button bt_cancel =(Button) view.findViewById(R.id.bt_cancle);
		
		
		
		bt_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//点击确定按钮之后再取值
				String pwd = et_pwd.getText().toString().trim();
				// TODO Auto-generated method stub
				if(!TextUtils.isEmpty(pwd)){
					//对比本地存储中的密码
					String spf_pwd = SharedPreferencesUtils.getString(getApplicationContext(), "pwd", "") ;
					String md5_pwd = MD5Utils.encode(pwd);
					if(spf_pwd.equals(md5_pwd)){
					//密码正确
						dialog.dismiss();
						startActivity(new Intent(getApplicationContext(),LostAndFindActivity.class));
					}else{
						ToastUtils.show(getApplicationContext(), "你输入的密码错误，请重新输入！");
					}
				}else{
					ToastUtils.show(getApplicationContext(), "你输入的密码为空！");
				}
			}	
		});
		bt_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
	/**
	 * 展现密码设置界面
	 */
	protected void showSetArlertDialog() {
		// TODO Auto-generated method stub
		Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog =builder.create();
		
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pwdset_dialog_layout, null);
		dialog.setView(view, 0, 0, 0, 0);
		
		final EditText et_pwd =(EditText) view.findViewById(R.id.et_pwd);
		final EditText et_confirm_pwd = (EditText) view.findViewById(R.id.et_confir_mpwd);
		
		Button bt_ok = (Button) view.findViewById(R.id.bt_ok);
		Button bt_cancel =(Button) view.findViewById(R.id.bt_cancle);
		
		
		bt_ok.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				String pwd = et_pwd.getText().toString().trim();
				String confirm_pwd = et_confirm_pwd.getText().toString().trim();
				// TODO Auto-generated method stub
				if(!TextUtils.isEmpty(pwd)&&!TextUtils.isEmpty(confirm_pwd)){
					//两个文本输入框都不为空
					if(pwd.equals(confirm_pwd)){
						//两次输入的密码相同
						SharedPreferencesUtils.putString(getApplicationContext(), "pwd", MD5Utils.encode(pwd));
						SharedPreferencesUtils.putBoolean(getApplicationContext(), true,"pwdSetted");
						
						dialog.dismiss();
						startActivity(new Intent(getApplicationContext(),Guide1Activity.class));
						
					}else{
						ToastUtils.show(getApplicationContext(), "两次输入的密码不一样！");
					}
				}else{
					ToastUtils.show(getApplicationContext(), "你输入的密码为空，请重新输入！");
				}
			}
		});
		bt_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
	/**
	 * 判断是否设置过手机防盗的密码
	 */
	protected boolean pwdBeSetted() {
		// TODO Auto-generated method stub
		return SharedPreferencesUtils.getBoolean(this, "pwdSetted", false);
	}
	/**
	 * 创建网格布局适配器类
	 * @author Jack Zhang
	 *
	 */
	class HomeAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mHomeItemNames.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout, null);
			TextView tv_name =(TextView) view.findViewById(R.id.tv_itemName);
			ImageView iv_home = (ImageView) view.findViewById(R.id.iv_itemImag);
			if(tv_name!=null){
				
				tv_name.setText(mHomeItemNames[position]);
			}else{
				Log.d("HomeActivity", "没有找到相应的对象！");
			}
			
			if(iv_home!=null){
				
				iv_home.setImageResource(mHomeIcons[position]);
			}else{
				Log.d("HomeActivity", "没有找到相应的对象！");
			}
			
			return view;
		}
		
		
	}

}
