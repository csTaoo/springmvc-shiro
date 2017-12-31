package com.shitao.foods.dao;

import java.util.List;

import com.shitao.common.persistence.dao.NotSysBaseDAO;
import com.shitao.foods.entity.Foods;

public interface FoodsDAO extends NotSysBaseDAO<Foods>{
	
	
	String getlastestNumber();
	
	List<Foods> listFoodsBySort(String id);

}
