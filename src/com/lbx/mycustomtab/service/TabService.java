package com.lbx.mycustomtab.service;

import com.lbx.mycustomtab.task.Task1;
import com.lbx.mycustomtab.util.ThreadPoolManager;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

public class TabService extends Service {

	Handler handler = new Handler(){
		
	};
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("TabService--onCreate()"+"-----"+Thread.currentThread());
	}
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("TabService--onBind()"+"-----"+Thread.currentThread());
		// TODO Auto-generated method stub
		return new DownloadBinder();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("TabService--onStartCommand()"+"-----"+Thread.currentThread());
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("TabService--onDestroy()"+"-----"+Thread.currentThread());
		ThreadPoolManager.getInstance().removeAllTask();
		super.onDestroy();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("TabService--onUnbind()"+"-----"+Thread.currentThread());
		return super.onUnbind(intent);
	}
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("TabService--onRebind()"+"-----"+Thread.currentThread());
		super.onRebind(intent);
	}
	@Override
	public void onTaskRemoved(Intent rootIntent) {
		// TODO Auto-generated method stub
		super.onTaskRemoved(rootIntent);
		System.out.println("TabService--onTaskRemoved()"+"-----"+Thread.currentThread());
	}
	class DownloadBinder extends Binder{
		public void start(){
			System.out.println("TabService.DownloadBinder--start()"+"-----"+Thread.currentThread());
			ThreadPoolManager.getInstance().addTask(new Task1(handler));
		}
		public void send(String msg){
			
		}
	}
	
}
