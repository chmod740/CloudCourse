package me.hupeng.web.cloudcourse.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("user")
public class User {
	@Id
	private int id;
	
	@Column("username")
	private String username;
	
	@Column("password")
	private String password;
	
	@Column("real_name")
	private String realName;
	
	/**
	 * 0：普通用户
	 * 1：教师用户
	 * 2：管理员用户
	 * */
	@Column("privilege")
	private int privilege;

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getPrivilege() {
		return privilege;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}
	
	
}
