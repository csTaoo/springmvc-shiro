package com.shitao.sys.entity;

public class Func extends BaseEntity {

	private String url;// 功能地址

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

}
