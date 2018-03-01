package com.shitao.comment.entity;

import java.util.List;

import org.bson.Document;

public class CommentJson {
	
	private int code;
	private int count;
	private List<Document> data;
	private String msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setData(List<Document> data) {
		this.data = data;
	}
	public List<Document> getData() {
		return data;
	}
	
	

}
