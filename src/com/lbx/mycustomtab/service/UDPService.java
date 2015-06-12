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
	 * 设置真实的uid
	 * @param uid
	 * @return
	 */
	public native int setUID(String uid);
	/**
	 * 可靠UDP的初始化
	 * @param uId  登录时的uid
	 * @param hostIP 连接的服务器地址
	 * @param port   连接的服务器端口号
	 * @return udp的号码
	 */
	public   native int initUDP(String uId,String hostIP,int port);
	/**
	 * 关闭UDP
	 * @return
	 */
	public   native int close();
	/**
	 * 发送
	 * @param sendMsgBytes 要发送的字节数组
	 * @param length  要发送的字节长度
	 * @return 0 表示发送成功，否则异常
	 */
	public   native int sendMsg(byte[] sendMsgBytes,int length);
	/**
	 * 接收
	 * @param waitMs 等待时常
	 * @param recvMsgBytes //接收的缓冲区
	 * @param length  //接收的缓冲区长度
	 * @return 当state>0 时，表示接收到字节数，否则异常
	 */
	public native int receiveMsg(int waitMs,byte[] recvMsgBytes,int length);
}
