package com.lbx.mycustomtab.task;

import com.lbx.mycustomtab.service.UDPService;
import com.lbx.mycustomtab.util.LogUtil;

public class InitUdpTask extends Thread{
	private Thread initThread;
	
	public InitUdpTask(Thread initThread) {
		super();
		this.initThread = initThread;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String ip = "";//Constant.getSERVER_HOST_IP();
		int port = 9999;//Constant.getSERVER_HOST_PORT();
//		LogUtil.iSave("infos",  "进入初始化循环体前：hadNotConnectedToService :"+hadNotConnectedToService+"/ initThread == Thread.currentThread() :"+(initThread == Thread.currentThread())+" / ip = "+ip+" / port = "+port);
		while(initThread == Thread.currentThread()){// 等到登录时才进行初始化工作，因为登录时 才能获取UID？？？？？？？？？？？
//			isInitingUdp = true;//表示正在初始化udp
//			int state = UDPService.getInstance().initUDP(ConfigUtil.getInstance().getConfigString(Constant.LOGIN_UID), Constant.SERVER_HOST_IP, Constant.SERVER_HOST_PORT);
			int state = UDPService.getInstance().initUDP("uid", ip, port);
			LogUtil.iSave("infos","执行底层初始化函数，initThread = "+Thread.currentThread()+" / 返回状态state = "+state);
			if(state > 0){//初始化成功，udp可以开始工作
//				LogUtil.iSave("infos", "初始化成功，udp可以开始工作.初始化的线程initThread = "+Thread.currentThread());
//				hadNotConnectedToService = false;
//				isUdpStartWorking = true;
//				isInitingUdp = false;//表示初始化完成
//				times1 = 0;
//				times2 = 0;
//				connectTime = 0;
//				receive();//             new Intent(Constant.ACTION_has_connected_service)
//				Instance.sendBroadcast(new Intent(Constant.ACTION_has_connected_service));//[再登录消息返回后再发送此广播，因为登录的返回大概需要4s]
//				send(new ConfigUtil().getConfigString(Constant.LOGIN_MSG)); //发送登录消息
//				initThread = null;//[0425初始化完成，就将初始化线程置空]
				break;
			}else if(state == 0){//??????????????????????????????????????????????????
				int result = UDPService.getInstance().close();//2014-2-21添加的
				LogUtil.iSave("infos", "udp初始化：0 系统已启动未关闭 / state = "+result);
//				hadNotConnectedToService = false;
//				isInitingUdp = false;//表示初始化完成
				break;
			}else if(state == -1){
				LogUtil.iSave("infos", "udp初始化：-1 系统错，初始化的线程initThread = "+Thread.currentThread());
//				hadNotConnectedToService = true;
//				isInitingUdp = false;//表示初始化错误中断
//				if(Instance == null)return;
//				Instance.sendBroadcast(new Intent(Constant.ACTION_Init_Net_Error));
				break;
			}else if(state == -2){
				LogUtil.iSave("infos", "udp初始化：-2 BIND端口错，初始化的线程initThread = "+Thread.currentThread());
//				hadNotConnectedToService = true;
//				isInitingUdp = false;//表示初始化错误中断
//				if(Instance == null)return;
//				Instance.sendBroadcast(new Intent(Constant.ACTION_Init_Net_Error));
				break;
			}else if(state == -3){
				LogUtil.iSave("infos", "udp初始化：-3 接收服务器回应超时initThread = "+Thread.currentThread());
				/*hadNotConnectedToService = true;//[0528 注释掉 登录进度 消失时 应该退出登录]
				if(Instance == null)return;
				connectTime++;
				if(connectTime % 15 == 0){//每连接15次后提示
					connectTime = 0;
					Instance.sendBroadcast(new Intent(Constant.ACTION_connect_service_timeout));
				}
				try { 
					if(TApplication.isBackgroudRun){//在后台运行时，才睡！！！
						times2 = 0;
						long time = (long) (10 *Math.pow((times1++), 2));//秒
						if(time >= 600)time = 600;
						LogUtil.iSave("time", "----后台睡眠时间-----time = "+time+" 秒");
						Thread.sleep(time * 1000);
					}else{//程序在前台时，就不用睡
						times1 = 0;
						long time = (long)(Math.pow(2, times2++));
						if(time >= 300)time = 300;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					LogUtil.iSave("time", ">>>>初始化线程-睡眠被打断<<<<<" );
				} */
				
			}
		}//[-end 循环结束]
	}

}
