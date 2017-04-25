package com.mina;
/**
 * 消息的定义
 * @author twj
 *
 */
import java.util.Date;
public class Message {
	
private String despId;//发送人
private String sendId;//接收人
private Date senddate;//发送时间
private Object msgdata;//发送数据

public String getDespId() {
	return despId;
}
public void setDespId(String despId) {
	this.despId = despId;
}
public String getSendId() {
	return sendId;
}
public void setSendId(String sendId) {
	this.sendId = sendId;
}
public Date getSenddate() {
	return senddate;
}
public void setSenddate(Date senddate) {
	this.senddate = senddate;
}
public Object getMsgdata() {
	return msgdata;
}
public void setMsgdata(Object msgdata) {
	this.msgdata = msgdata;
}

	
}
