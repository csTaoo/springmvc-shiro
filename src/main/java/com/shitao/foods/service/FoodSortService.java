package com.shitao.foods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitao.foods.dao.FoodSortDAO;
import com.shitao.foods.entity.FoodSort;

@Service
public class FoodSortService {
	
	
	@Autowired
	private FoodSortDAO foodSortDAO;

	public FoodSort get(String id)
	{
		return foodSortDAO.get(id);
	}
	
	public List<FoodSort> list()
	{
		return foodSortDAO.list();
	}
	
	public void update(FoodSort foods)
	{
		foodSortDAO.update(foods);
	}
	
	public void delete(FoodSort foods)
	{
		foodSortDAO.delete(foods);
	}
	
	public String getLastestNumber()
	{
		return foodSortDAO.getlastestNumber();
	}
	
	public void save(FoodSort foods)
	{
		foodSortDAO.save(foods);
	}
	
}
