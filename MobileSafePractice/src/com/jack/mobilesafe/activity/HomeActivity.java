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
	 * ��ʼ�����񲼾ֵ�����Դ
	 */
	private String[] mHomeItemNames = new String[]{
			"�ֻ�����", "ͨѶ��ʿ", "�������",
			"���̹���", "����ͳ��", "�ֻ�ɱ��", "��������", "�߼�����", "��������"
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
				//�����ֻ�����
				case 0:
					//�ж��Ƿ�Ҫ�������룬��������������
					if(!pwdBeSetted()){
						//��ʾ���������
						showSetArlertDialog();
					}else{
						//��ʾ�������ÿ�
						showInputAlertDialog();
					}
					break;
				//������������
				case 8:
					Log.d("HomeActivity", "����˵ھŸ���ť��");
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
	 * չʾ�����������
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
				//���ȷ����ť֮����ȡֵ
				String pwd = et_pwd.getText().toString().trim();
				// TODO Auto-generated method stub
				if(!TextUtils.isEmpty(pwd)){
					//�Աȱ��ش洢�е�����
					String spf_pwd = SharedPreferencesUtils.getString(getApplicationContext(), "pwd", "") ;
					String md5_pwd = MD5Utils.encode(pwd);
					if(spf_pwd.equals(md5_pwd)){
					//������ȷ
						dialog.dismiss();
						startActivity(new Intent(getApplicationContext(),LostAndFindActivity.class));
					}else{
						ToastUtils.show(getApplicationContext(), "�����������������������룡");
					}
				}else{
					ToastUtils.show(getApplicationContext(), "�����������Ϊ�գ�");
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
	 * չ���������ý���
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
					//�����ı�����򶼲�Ϊ��
					if(pwd.equals(confirm_pwd)){
						//���������������ͬ
						SharedPreferencesUtils.putString(getApplicationContext(), "pwd", MD5Utils.encode(pwd));
						SharedPreferencesUtils.putBoolean(getApplicationContext(), true,"pwdSetted");
						
						dialog.dismiss();
						startActivity(new Intent(getApplicationContext(),Guide1Activity.class));
						
					}else{
						ToastUtils.show(getApplicationContext(), "������������벻һ����");
					}
				}else{
					ToastUtils.show(getApplicationContext(), "�����������Ϊ�գ����������룡");
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
	 * �ж��Ƿ����ù��ֻ�����������
	 */
	protected boolean pwdBeSetted() {
		// TODO Auto-generated method stub
		return SharedPreferencesUtils.getBoolean(this, "pwdSetted", false);
	}
	/**
	 * �������񲼾���������
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
				Log.d("HomeActivity", "û���ҵ���Ӧ�Ķ���");
			}
			
			if(iv_home!=null){
				
				iv_home.setImageResource(mHomeIcons[position]);
			}else{
				Log.d("HomeActivity", "û���ҵ���Ӧ�Ķ���");
			}
			
			return view;
		}
		
		
	}

}
