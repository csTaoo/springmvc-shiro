package com.shitao.comment.entity;

import com.shitao.common.entity.AbstractEntity;

public class Comment extends AbstractEntity{
	
	private String userId;
	private String orderId;
	private	 String content;
	private short star;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public short getStar() {
		return star;
	}
	public void setStar(short star) {
		this.star = star;
	}
	
	
}
