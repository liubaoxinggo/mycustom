package com.lbx.mycustomtab.service;

public class UDPService {

	private static UDPService udpService;
	static{
		System.loadLibrary("UDP");
	}
	public static UDPService getInstance(){
		if(udpService == null){
			synchronized (UDPService.class) {
				if(udpService == null){
					udpService = new UDPService();
				}
			}
		}
		return udpService;
	}
	/**
	 * ������ʵ��uid
	 * @param uid
	 * @return
	 */
	public native int setUID(String uid);
	/**
	 * �ɿ�UDP�ĳ�ʼ��
	 * @param uId  ��¼ʱ��uid
	 * @param hostIP ���ӵķ�������ַ
	 * @param port   ���ӵķ������˿ں�
	 * @return udp�ĺ���
	 */
	public   native int initUDP(String uId,String hostIP,int port);
	/**
	 * �ر�UDP
	 * @return
	 */
	public   native int close();
	/**
	 * ����
	 * @param sendMsgBytes Ҫ���͵��ֽ�����
	 * @param length  Ҫ���͵��ֽڳ���
	 * @return 0 ��ʾ���ͳɹ��������쳣
	 */
	public   native int sendMsg(byte[] sendMsgBytes,int length);
	/**
	 * ����
	 * @param waitMs �ȴ�ʱ��
	 * @param recvMsgBytes //���յĻ�����
	 * @param length  //���յĻ���������
	 * @return ��state>0 ʱ����ʾ���յ��ֽ����������쳣
	 */
	public native int receiveMsg(int waitMs,byte[] recvMsgBytes,int length);
}
