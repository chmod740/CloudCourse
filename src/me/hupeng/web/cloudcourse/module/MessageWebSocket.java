package me.hupeng.web.cloudcourse.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import me.hupeng.web.cloudcourse.bean.Message;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * ���������Ϣ��Socket������.<br>
 * ע��:�����ʱ��JDK�汾������ڵ���1.8,Tomcat�汾������ڵ���8.
 * @author HUPENG
 * */
@ServerEndpoint(value = "/websocket", configurator=GetHttpSessionConfigurator.class)
@IocBean(singleton = true)
public class MessageWebSocket {
	
    /**
     * ���Websocket Session Id --> Session ��ӳ���ϵ
     */
    protected static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
	
    /**
     * ���Websocket Session Id --> Course ��ӳ���ϵ
     * */
    private static ConcurrentHashMap<String, Integer> courses = new ConcurrentHashMap<>();
    
    
    /**
     * ���Websocket Session Id --> Session ��ӳ���ϵ
     * */
    private static ConcurrentHashMap<String, HttpSession>httpSessions  = new ConcurrentHashMap<>();
    
    @Inject
    Dao dao;
    
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		sessions.put(session.getId(), session);
		
		HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName()); 
		httpSessions.put(session.getId(), httpSession);
		
		System.out.println("�Ự��" + session.getId() + " ���������");
    }
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason){
		sessions.remove(session.getId());
		courses.remove(session.getId());
		httpSessions.remove(session.getId());
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
     * �յ���Ϣ֮��ĵ��ô˷����򷿼��ڵ���������������Ϣ.<br>
     * �ݶ��ϴ�����Ϣ��ʽ������:<br>
     * �������뷿�䣺{"action": "in_course","course_id":1}
     * ������Ϣ��{"action":"send_msg","course_id":1,"msg":"this is a test message","video_time":3}<br>
     * 
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
		
		JsonParser parse =new JsonParser();
		JsonObject json=(JsonObject) parse.parse(message);
		String action = json.get("action").getAsString();
		
		switch (action) {
			case "in_course":
				int courseId = json.get("course_id").getAsInt();
				courses.put(session.getId(), courseId);
				break;
			case "send_msg":
				courseId = json.get("room_id").getAsInt();
				String msg = json.get("msg").getAsString();
				int videoTime = json.get("video_time").getAsInt();
	//			Message messageBean = new Message(courseId, userId, videoTime, message, sendTime)
				Message messageBean = null;
				
				try {
					messageBean = new Message(courseId, (Integer)httpSessions.get(session.getId()).getAttribute("user_id"), videoTime, msg, new Date(System.currentTimeMillis()));
				} catch (Exception e) {
					messageBean = new Message(courseId, 1, videoTime, msg, new Date(System.currentTimeMillis()));
				}
				dao.insert(messageBean);
				
				for(String key: courses.keySet()){
					if (courses.get(key).equals(courseId) && !key.equals(session.getId())) {
						sessions.get(key).getBasicRemote().sendText(msg);
					}
				}
				break;
			default:
				break;
		}
	}
}
