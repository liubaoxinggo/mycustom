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
//		LogUtil.iSave("infos",  "�����ʼ��ѭ����ǰ��hadNotConnectedToService :"+hadNotConnectedToService+"/ initThread == Thread.currentThread() :"+(initThread == Thread.currentThread())+" / ip = "+ip+" / port = "+port);
		while(initThread == Thread.currentThread()){// �ȵ���¼ʱ�Ž��г�ʼ����������Ϊ��¼ʱ ���ܻ�ȡUID����������������������
//			isInitingUdp = true;//��ʾ���ڳ�ʼ��udp
//			int state = UDPService.getInstance().initUDP(ConfigUtil.getInstance().getConfigString(Constant.LOGIN_UID), Constant.SERVER_HOST_IP, Constant.SERVER_HOST_PORT);
			int state = UDPService.getInstance().initUDP("uid", ip, port);
			LogUtil.iSave("infos","ִ�еײ��ʼ��������initThread = "+Thread.currentThread()+" / ����״̬state = "+state);
			if(state > 0){//��ʼ���ɹ���udp���Կ�ʼ����
//				LogUtil.iSave("infos", "��ʼ���ɹ���udp���Կ�ʼ����.��ʼ�����߳�initThread = "+Thread.currentThread());
//				hadNotConnectedToService = false;
//				isUdpStartWorking = true;
//				isInitingUdp = false;//��ʾ��ʼ�����
//				times1 = 0;
//				times2 = 0;
//				connectTime = 0;
//				receive();//             new Intent(Constant.ACTION_has_connected_service)
//				Instance.sendBroadcast(new Intent(Constant.ACTION_has_connected_service));//[�ٵ�¼��Ϣ���غ��ٷ��ʹ˹㲥����Ϊ��¼�ķ��ش����Ҫ4s]
//				send(new ConfigUtil().getConfigString(Constant.LOGIN_MSG)); //���͵�¼��Ϣ
//				initThread = null;//[0425��ʼ����ɣ��ͽ���ʼ���߳��ÿ�]
				break;
			}else if(state == 0){//??????????????????????????????????????????????????
				int result = UDPService.getInstance().close();//2014-2-21��ӵ�
				LogUtil.iSave("infos", "udp��ʼ����0 ϵͳ������δ�ر� / state = "+result);
//				hadNotConnectedToService = false;
//				isInitingUdp = false;//��ʾ��ʼ�����
				break;
			}else if(state == -1){
				LogUtil.iSave("infos", "udp��ʼ����-1 ϵͳ����ʼ�����߳�initThread = "+Thread.currentThread());
//				hadNotConnectedToService = true;
//				isInitingUdp = false;//��ʾ��ʼ�������ж�
//				if(Instance == null)return;
//				Instance.sendBroadcast(new Intent(Constant.ACTION_Init_Net_Error));
				break;
			}else if(state == -2){
				LogUtil.iSave("infos", "udp��ʼ����-2 BIND�˿ڴ���ʼ�����߳�initThread = "+Thread.currentThread());
//				hadNotConnectedToService = true;
//				isInitingUdp = false;//��ʾ��ʼ�������ж�
//				if(Instance == null)return;
//				Instance.sendBroadcast(new Intent(Constant.ACTION_Init_Net_Error));
				break;
			}else if(state == -3){
				LogUtil.iSave("infos", "udp��ʼ����-3 ���շ�������Ӧ��ʱinitThread = "+Thread.currentThread());
				/*hadNotConnectedToService = true;//[0528 ע�͵� ��¼���� ��ʧʱ Ӧ���˳���¼]
				if(Instance == null)return;
				connectTime++;
				if(connectTime % 15 == 0){//ÿ����15�κ���ʾ
					connectTime = 0;
					Instance.sendBroadcast(new Intent(Constant.ACTION_connect_service_timeout));
				}
				try { 
					if(TApplication.isBackgroudRun){//�ں�̨����ʱ����˯������
						times2 = 0;
						long time = (long) (10 *Math.pow((times1++), 2));//��
						if(time >= 600)time = 600;
						LogUtil.iSave("time", "----��̨˯��ʱ��-----time = "+time+" ��");
						Thread.sleep(time * 1000);
					}else{//������ǰ̨ʱ���Ͳ���˯
						times1 = 0;
						long time = (long)(Math.pow(2, times2++));
						if(time >= 300)time = 300;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					LogUtil.iSave("time", ">>>>��ʼ���߳�-˯�߱����<<<<<" );
				} */
				
			}
		}//[-end ѭ������]
	}

}
