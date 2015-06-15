package com.lbx.mycustomtab;

import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.entity.Fav;
import com.lbx.mycustomtab.entity.User;
import com.lbx.mycustomtab.service.TestServiceActivity;
import com.lbx.mycustomtab.util.DBHelper;
import com.lbx.mycustomtab.util.GetPathFromUri;

public class Launcher extends Activity implements OnClickListener{
	Button btn1,btn2,btn3,btn4,btn5,btn6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		init();
		initReceiver();
	}
	void init(){
		btn1 = (Button)findViewById(R.id.btn1);
		btn1.setText("tab 的各种显示11");
		btn1.setOnClickListener(this);
		btn2 = (Button)findViewById(R.id.btn2);
		btn2.setText("DownLoadManager");
		btn2.setOnClickListener(this);
		btn3 = (Button)findViewById(R.id.btn3);
		btn3.setText("动画   绘图  自定义view");
		btn3.setOnClickListener(this);
		btn4 = (Button)findViewById(R.id.btn4);
		btn4.setText("db  ");
		btn4.setOnClickListener(this);
		btn5 = (Button)findViewById(R.id.btn5);
		btn5.setText(" 测试service");
		btn5.setOnClickListener(this);
		btn6 = (Button)findViewById(R.id.btn6);
		btn6.setText("动画   绘图  自定义view");
		btn6.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == btn1){
			startActivity(new Intent(this, Tab.class));
		}else if(v == btn2){
			getPath();
//			download();
		}else if(v == btn3){
			startActivity(new Intent(this, AnimAndView.class));
		}else if(v == btn4){
			 test();
		}else if(v == btn5){
			startActivity(new Intent(Launcher.this, TestServiceActivity.class));
		}else if(v == btn6){
			startActivity(new Intent(this, AnimAndView01.class));
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
	void test(){
		User u = new User();
		u.name = "lbx";
		u.pwd = "123456";
		u.Tags = "刘宝兴";
		DBHelper.getInstance().saveUser(u);
		User u1 = new User();
		u1.name = "lsq";
		u1.pwd = "123456789";
		DBHelper.getInstance().saveUser(u1);
		Fav f = new Fav();
		f.name = "qwe";
		f.pwd = "0987654321";
		DBHelper.getInstance().saveFav(f);
		Fav f1 = new Fav();
		f1.name = "qaz";
		f1.pwd = "654321";
		f1.Tags = "刘少奇";
		DBHelper.getInstance().saveFav(f1);
	}
	void getIP(){
		new AsyncTask<Void, Void, Void>(){

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				try {
					InetAddress address = InetAddress.getByName("www.baidu.com");
//					InetAddress[] is = InetAddress.getAllByName("anhui.app.fhit.com.cn");
					InetAddress[] is = InetAddress.getAllByName("www.baidu.com");
//					InetAddress[] is = InetAddress.getAllByName("www.sina.com");
					for (int i = 0; i < is.length; i++) {
						address = is[i];
//						Log.i("infos", "address.getCanonicalHostName = "+address.getCanonicalHostName());
						Log.i("infos", "address.getHostAddress = "+address.getHostAddress());
						StringBuilder sb = new StringBuilder();
						byte[] b = address.getAddress();
						for (int j = 0; j < b.length; j++) {
							sb.append(b[i]).append(".");
						}
						Log.i("infos", "address.getAddress = "+sb.toString());
						Log.i("infos", "address.getHostName = "+address.getHostName());
						Log.i("infos", "================================ ");
					}
					
					byte[] b = {1,2,3,4,5,6,7,8};
					String s = new String(b, 2, 6);
					Log.i("infos", "================="+s);
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}.execute();
	}
	void getPath(){
		Uri uri = Uri.parse("content://downloads/my_downloads/3");
		Log.i("infos", "GetPathFromUri.getDataColumn : "+GetPathFromUri.getDataColumn(this, uri, null, null));
		Log.i("infos", "GetPathFromUri.getPath : "+GetPathFromUri.getPath(this, uri));
		Log.i("infos", "getScheme : "+uri.getScheme());
		Log.i("infos", "getPath : "+uri.getPath());
		Log.i("infos", "getPort : "+uri.getPort());
		Log.i("infos", "getAuthority : "+uri.getAuthority());
		Log.i("infos", "getEncodedPath : "+uri.getEncodedPath());
	}
	void download(){
		DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
		Uri uri = Uri.parse("http://app.fhit.com.cn/ucall/uploads/apks/ucallmaster.1.0.0.apk");
		DownloadManager.Request request = new Request(uri);
		request.setAllowedNetworkTypes(Request.NETWORK_WIFI);//只有连接到WiFi时才进行大文件的下载
		long reference = downloadManager.enqueue(request);
		Log.i("infos", "download() reference = "+reference);
	}
	void initReceiver(){
		IntentFilter  filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		registerReceiver(receiver, filter);
	}
	BroadcastReceiver receiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if(action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
				try {
					long refernce = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
					DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
					ParcelFileDescriptor pfd = downloadManager.openDownloadedFile(refernce);
					Uri uri = downloadManager.getUriForDownloadedFile(refernce);
					Log.i("infos", "文件下载："+uri);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(action.equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)){
				
			}else if(action.equals(DownloadManager.ACTION_VIEW_DOWNLOADS)){
				
			}
		}
	};
	
}

























