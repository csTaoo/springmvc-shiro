package com.shitao.foods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shitao.common.persistence.dao.NotSysBaseDAO;
import com.shitao.foods.entity.Foods;

public interface FoodsDAO extends NotSysBaseDAO<Foods>{
	
	
	String getlastestNumber();
	
	List<Foods> listFoodsBySort(String id);
	
	List<Foods> listByName(@Param(value="foodname")String foodname);
	
	void decreaseFoodCount(@Param(value="id")String id,@Param(value="count")int count);
}
