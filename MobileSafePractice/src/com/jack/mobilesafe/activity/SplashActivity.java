package com.jack.mobilesafe.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.AlreadyConnectedException;

import org.json.JSONException;
import org.json.JSONObject;

import com.jack.mobliesafe.utils.StreamUtils;
import com.jack.mobliesafepractice.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {
	
	private final int CODE_UPDATEDIALOG =1;
	private final int CODE_ENTERHOME=2;
	
	private TextView tvName;
	private TextView tvDownload;
	private ProgressBar pbLoading;
	private String mCurrentVersion;
	
	private int versionCode;
	private String description;
	private String mUrl;
	
	private Handler mHandler =new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case CODE_UPDATEDIALOG:
				showUpdateDialog();
				break;
			case CODE_ENTERHOME:
				enterHome();
				break;
			}
		}
	};
	
	@Override
	public void onCreate(Bundle SavedInstanceState){
		super.onCreate(SavedInstanceState);
		setContentView(R.layout.splash_activity);
		tvName=(TextView) findViewById(R.id.tv_name);
		tvDownload=(TextView) findViewById(R.id.tv_download);
		pbLoading=(ProgressBar) findViewById(R.id.pb_loading);
		mCurrentVersion = getVersion();
		
		tvName.setText("�汾��"+mCurrentVersion);
		
		tvDownload.setVisibility(View.GONE);
		checkVersion();
		
	}
	/**
	 * ����������
	 */
	protected void enterHome() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	protected void showUpdateDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("�汾����");
		builder.setMessage(description);
		builder.setPositiveButton("��������", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d("SplashActivity", "��ʼ������Ӧ��");
				downloadTask();
				
			}
		});
		builder.setNegativeButton("�Ժ���˵", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				enterHome();
				
			}
		});
		builder.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				enterHome();
			}
		});
		
		builder.show();
	}
	/**
	 * �����°�Ӧ��
	 */
	protected void downloadTask() {
		// TODO Auto-generated method stub
		//�ж�SD���Ƿ����
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			HttpUtils httpUtils =new HttpUtils();
			String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/mobilesafe66.apk";
			Log.d("SplashActivity","SD��·����"+path);
			httpUtils.download(mUrl, path, new RequestCallBack<File>() {
				
				@Override
				public void onLoading(long total, long current,
						boolean isUploading) {
					// TODO Auto-generated method stub
					super.onLoading(total, current, isUploading);
					long percent = 100*current/total;
					Log.d("SlashActivity","���ؽ���:"+percent);
					tvDownload.setText("���ؽ��ȣ�"+percent +"%");
					tvDownload.setVisibility(View.INVISIBLE);
				}

				@Override
				public void onSuccess(ResponseInfo<File> info) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					intent.setDataAndType(Uri.fromFile(info.result), "application/vnd.android.package-archive");
					
					startActivityForResult(intent, 0);
					
				}
				
				@Override
				public void onFailure(HttpException erro, String msg) {
					erro.printStackTrace();
					Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
					
				}
			});
		}else{
			Toast.makeText(this, "��ȷ�ϲ�����SD��", Toast.LENGTH_SHORT).show();
		}
	}
	/**
	 * ���汾
	 */
	private void checkVersion() {
		// TODO Auto-generated method stub
		new Thread(){
			Message msg=Message.obtain();
			long startTiem=System.currentTimeMillis();

			@Override
			public void run(){
				try {
					HttpURLConnection conn = (HttpURLConnection) new URL("http://10.0.2.2:8080/update66.json").openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(2000);
					conn.setReadTimeout(2000);
					
					conn.connect();
					int responseCode=conn.getResponseCode();
					if(responseCode==200){
						InputStream in = conn.getInputStream();
						String result = StreamUtils.stream2String(in);
						Log.d("SplashActivity", "result:"+result);
						JSONObject jo =new JSONObject(result);
						String version = jo.getString("versionName");
						versionCode = jo.getInt("versionCode");
						description = jo.getString("des");
						mUrl = jo.getString("url");
						Log.d("SplashActivity","�����ȡ�İ汾��"+version);
						
						if(getVersionCode()<versionCode){
							//��ǰ�汾С������汾����Ҫ����
							msg.what = CODE_UPDATEDIALOG;
							
						}else{
							//ֱ�ӽ���Ӧ��
							msg.what = CODE_ENTERHOME;
						}
						
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					long endTime=System.currentTimeMillis();
					long usedTime = endTime -startTiem;
					
					if(usedTime<2000){
						try {
							sleep(2000-usedTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					mHandler.sendMessage(msg);
				}
			}
		}.start();
	}
	//��ȡӦ�ð汾��Ϣ�ķ���
	private String getVersion() {
		// TODO Auto-generated method stub
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	//��ȡӦ�ð汾��Ϣ�ķ���
	private int getVersionCode() {
		// TODO Auto-generated method stub
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		enterHome();
	}

	
}
