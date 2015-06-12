package com.lbx.mycustomtab.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
	private ExecutorService service;
	
	private ThreadPoolManager(){
//		int num = Runtime.getRuntime().availableProcessors();// 
//		service = Executors.newFixedThreadPool(num*2);
		service = Executors.newCachedThreadPool();//����һ���ɸ�����Ҫ�������̵߳��̳߳أ���������ǰ������߳̿���ʱ����������
	}
	
	private static ThreadPoolManager manager;
	
	
	public static ThreadPoolManager getInstance(){
		if(manager==null){
			synchronized (ThreadPoolManager.class) {
				if(manager == null){
					manager= new ThreadPoolManager();
				} 
			}
		}
		return manager;
	}
	
	public void addTask(Runnable runnable){
		service.submit(runnable);
	}
	public void removeAllTask(){
//		service.shutdown();// ����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�����������������Ѿ��رգ������û����������
//		service.shutdownNow();//��ͼֹͣ��������ִ�еĻ������ͣ�������ڵȴ������񣬲����صȴ�ִ�е������б�
	}
}
