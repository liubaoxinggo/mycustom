package com.lbx.mycustomtab.task;

import android.os.Handler;

public class Task1 implements Runnable {

	private Handler handler;
	int i = 0;
	
	
	public Task1(Handler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				if(i == 20)break;
				int num = Runtime.getRuntime().availableProcessors();
				System.out.println("ִ������......"+(i++)+"-----"+Thread.currentThread()+" / ����������Ŀ = "+num);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("ִ�������쳣...InterruptedException..."+i+"-----"+Thread.currentThread());
				break;
			}
		}
	}

}
