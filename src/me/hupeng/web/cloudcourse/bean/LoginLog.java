package me.hupeng.web.cloudcourse.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 用户的登录记录的功能
 * */
@Table("login_log")
public class LoginLog {
	@Id
	private int id;
	
	@Column("username")
	private String username;
	
	@Column("password")
	private String password;
	
	@Column("login_time")
	private Date loginTime;
	
	/**
	 * 登录结果
	 * 1:代表登录成功
	 * 其他数字:代表登录失败
	 * */
	@Column("login_result")
	private int loginResult;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public int getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(int loginResult) {
		this.loginResult = loginResult;
	}
	
	
}
