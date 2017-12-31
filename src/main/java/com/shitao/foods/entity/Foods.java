package com.shitao.foods.entity;

import com.shitao.common.entity.AbstractEntity;

public class Foods extends AbstractEntity{
	
	private String food_name;
	private double food_price;
	private double food_discount;
	private int food_num;
	private String food_img;
	private FoodSort foodSort;
	/**
	 * @return the food_name
	 */
	public String getFood_name() {
		return food_name;
	}
	/**
	 * @param food_name the food_name to set
	 */
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	/**
	 * @return the food_price
	 */
	public double getFood_price() {
		return food_price;
	}
	/**
	 * @param food_price the food_price to set
	 */
	public void setFood_price(double food_price) {
		this.food_price = food_price;
	}
	/**
	 * @return the food_discount
	 */
	public double getFood_discount() {
		return food_discount;
	}
	/**
	 * @param food_discount the food_discount to set
	 */
	public void setFood_discount(double food_discount) {
		this.food_discount = food_discount;
	}
	/**
	 * @return the foodSort
	 */
	public FoodSort getFoodSort() {
		return foodSort;
	}
	/**
	 * @param foodSort the foodSort to set
	 */
	public void setFoodSort(FoodSort foodSort) {
		this.foodSort = foodSort;
	}
	/**
	 * @return the food_num
	 */
	public int getFood_num() {
		return food_num;
	}
	/**
	 * @param food_num the food_num to set
	 */
	public void setFood_num(int food_num) {
		this.food_num = food_num;
	}
	/**
	 * @return the food_img
	 */
	public String getFood_img() {
		return food_img;
	}
	/**
	 * @param food_img the food_img to set
	 */
	public void setFood_img(String food_img) {
		this.food_img = food_img;
	}
	

}
