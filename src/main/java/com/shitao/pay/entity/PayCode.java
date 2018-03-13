package com.shitao.pay.entity;

import com.shitao.common.entity.AbstractEntity;

public class PayCode extends AbstractEntity{
	
	private String path;
	private short status;
	private String time;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
