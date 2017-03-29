package me.hupeng.web.cloudcourse.module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.http.Http;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;


@IocBean
public class AuthorityFilter implements ActionFilter{
	@Inject
	Dao dao;
	
	@Override
	public View match(ActionContext actionContext) {
		// TODO Auto-generated method stub
		String username = actionContext.getRequest().getParameter("username");
//		String ak = actionContext.getRequest().getParameter("ak");
//		if (username == null || ak == null || dao.fetch(User.class, Cnd.where("username", "=", username).and("ak","=",ak)) == null) {
//			return new View() {
//				@Override
//				public void render(HttpServletRequest request, HttpServletResponse response,
//						Object arg2) throws Throwable {
//					// TODO Auto-generated method stub
//					response.setStatus(403);
//					response.getOutputStream().write("<!DOCTYPE html><html><head><title>Apache Tomcat/8.5.4 - Error report</title><style type=\"text/css\">H1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;} H2 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:16px;} H3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;} BODY {font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;} B {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;} P {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;}A {color : black;}A.name {color : black;}.line {height: 1px; background-color: #525D76; border: none;}</style> </head><body><h1>HTTP Status 403 - </h1><div class=\"line\"></div><p><b>type</b> Status report</p><p><b>message</b> <u></u></p><p><b>description</b> <u>Access to the specified resource has been forbidden.</u></p><hr class=\"line\"><h3>Apache Tomcat/8.5.4</h3></body></html>".getBytes());
//				}
//			};
//		}
//		actionContext.getRequest().getSession().get
		return null;
	}
}
