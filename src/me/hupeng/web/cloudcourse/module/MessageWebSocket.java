package me.hupeng.web.cloudcourse.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.nutz.ioc.loader.annotation.IocBean;

/**
 * ���������Ϣ��Socket������.<br>
 * ע��:�����ʱ��JDK�汾������ڵ���1.8,Tomcat�汾������ڵ���8.
 * @author HUPENG
 * */
@ServerEndpoint(value = "/websocket")
@IocBean(singleton = true)
public class MessageWebSocket {
	
    /**
     * ���Websocket Session Id --> Session ��ӳ���ϵ
     */
    protected static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(Session session) {
		sessions.put(session.getId(), session);
        System.out.println("�Ự��" + session.getId() + " ���������");
    }
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason){
		sessions.remove(session.getId());
		System.out.println("�Ự��"+ session.getId() + " �뿪������");
	}
	
    /**
     * WebSocket�Ự����ʱ����,Ĭ�ϵ���onClose.
     */
    public void onError(Session session, java.lang.Throwable throwable) {
    	System.out.println("�Ự��"+ session.getId() + " ��������");
    	onClose(session, null);
    }
   
    /**
     * �յ���Ϣ֮��ĵ��ô˷���.<br>
     * �ݶ��ϴ�����Ϣ��ʽ������:<br>
     * {"room_id":1,"msg":"this is a test message","time":3}<br>
     * ��Ϣ��ʽΪJson��ʽ,����time�ֶ�Ϊ���ʹ���Ϣʱ��Ƶ��ʱ������Ϣ,�����.<br>
     * @author		HUPENG
     * @version		0.0.1
     * @param		message	��������ϴ�����Ϣ
     * @param		session	��ʶ������˵�session����
     * @exception	IOException �ڷ�����Ϣ��ʱ����ܷ�������������쳣
     * @return		void
     * */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException {
		System.out.println("�յ� �Ự: " + session.getId() + " ����Ϣ��" + message + "��");
		
		
		for (String key : sessions.keySet()){
			try {
				sessions.get(key).getBasicRemote().sendText(session.getId() + ":" + message);
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
	}
}
