package com.shitao.common.entity;

import com.shitao.foods.utils.NumberCreater;


public abstract class AbstractEntity {
	
	public AbstractEntity() {
		// TODO Auto-generated constructor stub
		this.create_time = NumberCreater.getCurrentTime();
		
	}
	
	private String id;
	private String create_time;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the create_time
	 */
	public String getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(String create_time) {
		
		if(create_time.endsWith(".0"))
		{
			int loc = create_time.indexOf(".");
			this.create_time = create_time.substring(0, loc);
		}
		else
		{
			this.create_time = create_time ;
		}
	}
	
	
}
