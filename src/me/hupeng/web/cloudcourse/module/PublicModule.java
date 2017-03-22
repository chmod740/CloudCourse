package me.hupeng.web.cloudcourse.module;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.websocket.Session;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Each;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
public class PublicModule {
	@Inject
	MessageWebSocket messageWebSocket;
//	@Inject
//	MyWebsocket myWebsocket;
	
	@Ok("re")
	@Fail("http:500")
	@At("login")
	public String login(){

		return "jsp:1";
		
	}
	
	
}
