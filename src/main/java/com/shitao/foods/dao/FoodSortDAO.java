package com.shitao.foods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shitao.common.persistence.dao.NotSysBaseDAO;
import com.shitao.foods.entity.FoodSort;

public interface FoodSortDAO extends NotSysBaseDAO<FoodSort>{
	
	
	String getlastestNumber();
	
	List<FoodSort> listByName(@Param(value="sortname")String name);
}
