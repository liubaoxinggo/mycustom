package com.lbx.mycustomtab.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.service.TabService.DownloadBinder;

public class TestServiceActivity extends Activity implements OnClickListener{

	DownloadBinder binder;
	Intent service;
	Button btn1,btn2,btn3,btn4,btn5;
	Boolean isBind;
	ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			String s = null;
			if(name != null){
				s = name.getClassName();
			}
			System.out.println("TestServiceActivity.ServiceConnection--onServiceDisconnected() ComponentName = "+s+"-----"+Thread.currentThread());
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			String s = null;
			if(name != null){
				s = name.getClassName();
			}
			binder = (DownloadBinder)service;
			System.out.println("TestServiceActivity.ServiceConnection--onServiceConnected() ComponentName = "+s+"-----"+Thread.currentThread());
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		service = new Intent(this, TabService.class);
		
		btn1 = (Button)findViewById(R.id.btn1);
		btn1.setText("��service");
		btn1.setOnClickListener(this);
		btn2 = (Button)findViewById(R.id.btn2);
		btn2.setText("ִ�з���");
		btn2.setOnClickListener(this);
		btn3 = (Button)findViewById(R.id.btn3);
		btn3.setText("�����service");
		btn3.setOnClickListener(this);
		
		btn4 = (Button)findViewById(R.id.btn4);
		btn4.setText("����service");
		btn4.setOnClickListener(this);
		btn5 = (Button)findViewById(R.id.btn5);
		btn5.setText("ֹͣservice");
		btn5.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == btn1){
			isBind = this.bindService(service, conn, Context.BIND_AUTO_CREATE);
			System.out.println("��service �ɹ����"+isBind+"-----"+Thread.currentThread());
		}else if(v == btn2){
			System.out.println("ִ�з��� binder = "+binder+"-----"+Thread.currentThread());
			if(binder != null){
				binder.start();
			}
		}else if(v == btn3){
			if(isBind){
				System.out.println(" ����� "+"-----"+Thread.currentThread() );
				this.unbindService(conn);
				isBind = false;
				binder = null;
			}else{
				System.out.println(" δ�󶨣��޷������ "+"-----"+Thread.currentThread());
			}
		}else if(v == btn4){
			System.out.println(" ����service "+"-----"+Thread.currentThread() );
			this.startService(service);
		}else if(v == btn5){
			System.out.println(" ֹͣservice "+"-----"+Thread.currentThread() );
			this.stopService(service);
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		System.out.println("TestServiceActivity -- onDestroy()"+"-----"+Thread.currentThread());
	}
}
