package me.hupeng.web.cloudcourse.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 房间的消息的存储
 * */
@Table("message")
public class Message {
	@Id
	private int id;
	
	@Column("course_id")
	private int courseId;
	
	@Column("user_id")
	private int userId;
	
	@Column("video_time")
	private int videoTime;
	
	@Column("message")
	private String message;
	
	@Column("send_time")
	private Date sendTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(int videoTime) {
		this.videoTime = videoTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	
}
