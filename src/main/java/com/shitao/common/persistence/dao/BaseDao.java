package com.shitao.common.persistence.dao;

import java.util.List;

import com.shitao.sys.entity.BaseEntity;

public interface BaseDao<T extends BaseEntity> {

	T get(String id);
	
	void update(T t);
	
	List<T> list();
	
	void save(T t);
	
	void delete(T t);
}
