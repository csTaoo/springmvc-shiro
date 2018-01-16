package com.shitao.common.entity;

public class ResponseJson {
	
	
	private String httpStatus;
	private String message;
	
	public ResponseJson(String status ,String mes)
	{
		this.httpStatus = status;
		this.message = mes;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
