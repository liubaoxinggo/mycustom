package com.lbx.mycustomtab.task;

import java.io.UnsupportedEncodingException;

import com.lbx.mycustomtab.service.UDPService;
import com.lbx.mycustomtab.util.LogUtil;

public class SendMsgTask implements Runnable {

	private String msg;
	
	public SendMsgTask(String sendMsg) {
		super();
		this.msg = sendMsg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			byte[] buf = null;
			if(msg.charAt(0) == '0'){
				buf = msg.getBytes("GB2312");
			}else if(msg.charAt(0) == '1'){
				buf = msg.getBytes("GB2312");
			}
			int state = -100;
			if(buf != null){
				state = UDPService.getInstance().sendMsg(buf, buf.length);
			}
			switch (state) {
			case 0://���ͳɹ�
				LogUtil.iSave("infos", "====>>"+msg);
				break;
			case -2:
				LogUtil.iSave("infos", "====>����״̬��-2 ϵͳ��/"+msg );
//				if(Instance == null)return;
//				Instance.sendBroadcast(new Intent(Constant.ACTION_lost_service_connect_1));
//				Instance.reInit(true);
			    break;
			case -3:
				LogUtil.iSave("infos", "====>����״̬��-3 iCliSockID��Ч/"+msg );
//				if(Instance == null)return;
//				Instance.sendBroadcast(new Intent(Constant.ACTION_lost_service_connect_1));
//				Instance.reInit(true);
				break;
			case -4:
				LogUtil.iSave("infos", "====>����״̬��-4�Է��ѹر�SOCK/" +msg);
//				if(Instance == null)return;
//				//���͹㲥֪ͨ   ʧȥ����������
//				Instance.sendBroadcast(new Intent(Constant.ACTION_lost_service_connect_1));
//				Instance.reInit(true);
				break;
			case -1:
				LogUtil.iSave("infos", "====>����״̬��-1 ��δ��ʼ����Ҫ���͵�msg = "+msg );
//				if(Instance == null)return;
				//���͹㲥֪ͨ   ʧȥ����������
//				Instance.sendBroadcast(new Intent(Constant.ACTION_lost_service_connect_1));
				//Instance.reInit(true);
				break;
			default:
				LogUtil.eSave("infos", "====>����״̬�� -100 ������Ϣ��ʽ������ state = "+state+"/ msg = "+msg );
				break;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			LogUtil.e("infos", "SendMsgTask UnsupportedEncodingException 0x01:"+msg, e);
		}
	}
}








