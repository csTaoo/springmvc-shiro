package com.shitao.foods.dao;

import com.shitao.common.persistence.dao.NotSysBaseDAO;
import com.shitao.foods.entity.FoodSort;

public interface FoodSortDAO extends NotSysBaseDAO<FoodSort>{
	
	
	String getlastestNumber();
}
