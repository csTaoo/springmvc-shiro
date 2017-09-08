package com.shitao.common.persistence.dao;

import com.shitao.sys.entity.BaseEntity;

public interface BaseDao<T extends BaseEntity> {

	T get(String id);
	
	void update(T t)throws Exception;
}
