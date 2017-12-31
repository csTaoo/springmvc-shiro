package com.shitao.mongodb.operator;

import java.util.List;

public interface IMongoDBOperater<E> {
	
	/*
	 * 从集合插入一个文档
	 */
	void insert(E e);
	
	/*
	 * 从集合删除一个文档
	 */
	void delete(E e);
	
	/*
	 * 更新一个文档
	 */
	void update(E e);
	
	/*
	 * 获取集合所有文档
	 */
	List<E> list();
	
	/*
	 * 获取一个文档
	 */
	E get();
	
	/*
	 * 关闭连接
	 */
	void close();
	

}
