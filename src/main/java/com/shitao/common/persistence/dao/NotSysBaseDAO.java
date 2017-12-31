package com.shitao.common.persistence.dao;

import java.util.List;

import com.shitao.common.entity.AbstractEntity;

/**
 * 非系统模块继承
 * @author ：shitao.Chen
 * @date：2017年11月7日下午5:22:04
 * @className：NotSysBaseDAO
 * TODO
 * @param <E>
 */
public interface NotSysBaseDAO<E extends AbstractEntity> {
	
	E get(String id);
	
	List<E> list();
	
	void update(E e);
	
	void delete(E e);
	
	void save(E e);
}
