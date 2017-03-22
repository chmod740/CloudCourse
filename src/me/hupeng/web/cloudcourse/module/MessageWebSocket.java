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
//		// Print the client message for testing purposes
//		System.out.println("Received: " + message);
//
//		// Send the first message to the client
//		session.getBasicRemote().sendText("This is the first server message");
//
//		// Send 3 messages to the client every 5 seconds
//		int sentMessages = 0;
//		while (sentMessages < 3) {
//			session.getBasicRemote().sendText(
//					"This is an intermediate server message. Count: "
//							+ sentMessages);
//			sentMessages++;
//		}
//		
//		// Send a final message to the client
//		session.getBasicRemote().sendText("This is the last server message");
		
		
	}
}
