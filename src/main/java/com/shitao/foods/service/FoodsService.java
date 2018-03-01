package com.shitao.foods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitao.foods.dao.FoodSortDAO;
import com.shitao.foods.dao.FoodsDAO;
import com.shitao.foods.entity.Foods;

@Service
public class FoodsService {
	
	@Autowired
	private FoodsDAO foodsDAO;
	
	@Autowired
	private FoodSortDAO foodSortDAO;

	public Foods get(String id)
	{
		return foodsDAO.get(id);
	}
	
	public List<Foods> list()
	{
		return foodsDAO.list();
	}
	
	public List<Foods> list(String foodname)
	{
		return foodsDAO.listByName(foodname);
	}
	
	public void update(Foods foods)
	{
		foodsDAO.update(foods);
	}
	
	public void delete(Foods foods)
	{
		foodsDAO.delete(foods);
	}
	
	public String getLastestNumber()
	{
		return foodsDAO.getlastestNumber();
	}
	
	public void save(Foods foods)
	{
		foodsDAO.save(foods);
	}
	
	public List<Foods> listFoodsBySort(String sortId)
	{
		return foodsDAO.listFoodsBySort(sortId);
	}
	
	public void decreaseFoodCount(String id,int count)
	{
		foodsDAO.decreaseFoodCount(id,count);
	}
}
