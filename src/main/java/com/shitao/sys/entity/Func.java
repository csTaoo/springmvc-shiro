package com.shitao.sys.entity;

public class Func extends BaseEntity {

	private String url;// 功能地址
	private short  status;//状态0启用 1禁用

	public Func() {
		super();
	}

	public Func(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the status
	 */
	public short getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(short status) {
		this.status = status;
	}

}
