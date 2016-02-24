package com.jack.mobilesafe.activity;

import com.jack.mobliesafepractice.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
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
