package me.hupeng.web.cloudcourse.module;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import me.hupeng.ipLocationService.IpLocationResult;
import me.hupeng.ipLocationService.IpLocationService;
import me.hupeng.web.cloudcourse.bean.LoginLog;
import me.hupeng.web.cloudcourse.bean.User;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Each;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;


@Filters(@By(type=CheckSession.class, args={"user_id", "/html/login.php"}))
@IocBean
public class PublicModule {
	@Inject
	Dao dao;
	

	
	/**
	 * ִ���û��ĵ�¼����.<br>
	 * ��ʱ����ɵ�¼�������У�鹦��.<br>
	 * @author 	HUPENG
	 * @version 0.0.1
	 * @param	username	��¼ʱ������û���
	 * @param	password	��¼ʱ���������
	 * @return	Josn����<br>
	 * 			�ɹ�������{"state":0,"mag":"ok","data":{"username":"admin","realName":"����Ա","privilege":1}}<br>
	 * 			ʧ������1��{"state":-1,"msg":"username or password incorrect"}<br>
	 * 			ʧ������2��
	 * */
	@Ok("json:{locked:'password|id'}")
	@Fail("http:403")
	@At("login")
	@Filters
	public Object loginJson(HttpServletRequest request, HttpSession session,@Param("username")String username, @Param("password")String password){
		Map<String , Object>result = new LinkedHashMap<>();
		
		String ipAddress = request.getRemoteAddr();
		
		IpLocationResult ipLocationResult = new  IpLocationService().getIpLocationResult(ipAddress);
		String ipLocation = ipLocationResult.getCountry() + " " + ipLocationResult.getProvince() + " " + ipLocationResult.getCity();
		User user = dao.fetch(User.class, Cnd.where("username", "=", username).and("password", "=", password));
		if (username != null && password != null &&!(user == null)) {
			result.put("state", 0);
			result.put("mag", "ok");
			result.put("data", user);
			dao.insert(new LoginLog(username, password, request.getRemoteAddr(), ipLocation, new Date(System.currentTimeMillis()), 0));
			session.setAttribute("user_id", user.getId());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("user_privilege", user.getPrivilege());
		}else {
			result.put("state", -1);
			result.put("msg", "username or password incorrect");
			dao.insert(new LoginLog(username, password, request.getRemoteAddr(), ipLocation, new Date(System.currentTimeMillis()), -1));
		}
		return result;
	}
	
	@Ok("re")
	@Fail("http:403")
	@At("html/login")
	@Filters
	public Object loginHtml(HttpServletRequest request, HttpSession session,@Param("username")String username, @Param("password")String password){
		if (username == null || password == null) {
			return "jsp:/html/login";
		}
		String ipAddress = request.getRemoteAddr();
		IpLocationResult ipLocationResult = new  IpLocationService().getIpLocationResult(ipAddress);
		String ipLocation = ipLocationResult.getCountry() + " " + ipLocationResult.getProvince() + " " + ipLocationResult.getCity();
		User user = dao.fetch(User.class, Cnd.where("username", "=", username).and("password", "=", password));
		if (username != null && password != null &&!(user == null)) {

			dao.insert(new LoginLog(username, password, request.getRemoteAddr(), ipLocation, new Date(System.currentTimeMillis()), 0));
			session.setAttribute("user_id", user.getId());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("user_privilege", user.getPrivilege());
			return "redirect:/html/index.php";
		}else {

			dao.insert(new LoginLog(username, password, request.getRemoteAddr(), ipLocation, new Date(System.currentTimeMillis()), -1));
			
			request.setAttribute("message", "�û��������������");
			return "jsp:/html/login";
		}
//		return result;
	}
	
	@At("html/index")
	@Ok("re")
	@Fail("http:500")
	public String indexHtml(){
		return "jsp:/html/index";
	}
	
	/**
	 * ����һ��Ĭ�ϵ���ҳ
	 * */
	@At("index")
	@Ok("re")
	@Fail("http:500")
	public String index(){
		
		return "jsp:test";
	}
}
