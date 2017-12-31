package com.shitao.sys.entity;

import java.sql.Date;

public class Carousel extends BaseEntity{

	private String imgPath;
	private Date create_time;
	private short isVaild;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	

	public short getIsVaild() {
		return isVaild;
	}

	public void setIsVaild(short isVaild) {
		this.isVaild = isVaild;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	
	
}
