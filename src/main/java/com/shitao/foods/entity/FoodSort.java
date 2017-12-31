package com.shitao.foods.entity;

import java.util.List;

import com.shitao.common.entity.AbstractEntity;

public class FoodSort extends AbstractEntity{
	
	private String sort_name;
	private List<Foods> foods;
	/**
	 * @return the sort_name
	 */
	public String getSort_name() {
		return sort_name;
	}

	/**
	 * @param sort_name the sort_name to set
	 */
	public void setSort_name(String sort_name) {
		this.sort_name = sort_name;
	}

	public List<Foods> getFoods() {
		return foods;
	}

	public void setFoods(List<Foods> foods) {
		this.foods = foods;
	}
	
}
