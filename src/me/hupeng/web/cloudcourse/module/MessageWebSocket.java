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
 * 处理房间的消息的Socket服务类.<br>
 * 注意:部署的时候JDK版本必须大于等于1.8,Tomcat版本必须大于等于8.
 * @author HUPENG
 * */
@ServerEndpoint(value = "/websocket")
@IocBean(singleton = true)
public class MessageWebSocket {
	
    /**
     * 存放Websocket Session Id --> Session 的映射关系
     */
    protected static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(Session session) {
		sessions.put(session.getId(), session);
        System.out.println("会话：" + session.getId() + " 连入服务器");
    }
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason){
		sessions.remove(session.getId());
		System.out.println("会话："+ session.getId() + " 离开服务器");
	}
	
    /**
     * WebSocket会话出错时调用,默认调用onClose.
     */
    public void onError(Session session, java.lang.Throwable throwable) {
    	System.out.println("会话："+ session.getId() + " 发生错误");
    	onClose(session, null);
    }
   
    /**
     * 收到消息之后的调用此方法.<br>
     * 暂定上传的消息格式类似于:<br>
     * {"room_id":1,"msg":"this is a test message","time":3}<br>
     * 消息格式为Json格式,其中time字段为发送此信息时视频的时间轴信息,以秒计.<br>
     * @author		HUPENG
     * @version		0.0.1
     * @param		message	浏览器端上传的消息
     * @param		session	标识浏览器端的session对象
     * @exception	IOException 在发送消息的时候可能发生的输入输出异常
     * @return		void
     * */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException {
		System.out.println("收到 会话: " + session.getId() + " 的消息（" + message + "）");
		
		
		for (String key : sessions.keySet()){
			try {
				sessions.get(key).getBasicRemote().sendText(session.getId() + ":" + message);
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
	}
}
