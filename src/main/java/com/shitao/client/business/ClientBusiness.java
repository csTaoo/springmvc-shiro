package com.shitao.client.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shitao.foods.entity.FoodSort;
import com.shitao.foods.entity.Foods;
import com.shitao.foods.service.FoodSortService;
import com.shitao.foods.service.FoodsService;
import com.shitao.sys.entity.Carousel;
import com.shitao.sys.service.BusinessService;

/*
 * 外观类
 * 负责用户点餐页面的所有业务
 */
@Component
public class ClientBusiness {
	
	@Autowired
	private FoodSortService foodSortService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private FoodsService foodsService;
	
	public List<FoodSort> listFoodSort()
	{
		return foodSortService.list();
	}
	
	public List<Carousel> listCarousel()
	{
		return businessService.listToshow();
	}
	
	public void pay(String username,double money)
	{
		businessService.pay(username, money);
	}
	
	
	public void decreaseFoodCount(String id,int count)
	{
		foodsService.decreaseFoodCount(id, count);
	}
	
	public double getUserMoney()
	{
		return businessService.getUserMoney();
	}
	
	public Foods getFoodsById(String id)
	{
		return foodsService.get(id);
	}
}
