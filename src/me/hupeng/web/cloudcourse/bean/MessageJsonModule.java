package me.hupeng.web.cloudcourse.bean;

public class MessageJsonModule extends Message{
	private long sendTimestamp;
	
	
	
	public long getSendTimestamp() {
		return sendTimestamp;
	}



	public void setSendTimestamp(long sendTimestamp) {
		this.sendTimestamp = sendTimestamp;
	}



	public MessageJsonModule(Message message){
		super.setCourseId(message.getCourseId());
		super.setMessage(message.getMessage());
		super.setSendTime(message.getSendTime());
		super.setVideoTime(message.getVideoTime());
		this.setSendTimestamp(message.getSendTime().getTime());
	}
}
