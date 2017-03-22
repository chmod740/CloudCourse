//package me.hupeng.web.cloudcourse.module;
//
//import java.io.IOException;
//
//import javax.websocket.OnMessage;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//
//import org.nutz.ioc.loader.annotation.IocBean;
//import org.nutz.mvc.Mvcs;
//import org.nutz.plugins.mvc.websocket.AbstractWsEndpoint;
//import org.nutz.plugins.mvc.websocket.NutWsConfigurator;
//
//@ServerEndpoint(value = "/websocket", configurator = NutWsConfigurator.class)
//@IocBean(create="init", depose="depose")
//public class MyWebsocket extends AbstractWsEndpoint {
//
//	@OnMessage
//	public void onMessage(String message, Session session) throws IOException,
//			InterruptedException {
//
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
//	}
//}
