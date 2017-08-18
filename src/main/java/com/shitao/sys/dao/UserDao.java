/**
 * 
 */
package com.shitao.sys.dao;

import java.util.Collection;

import com.shitao.common.persistence.dao.BaseDao;
import com.shitao.sys.entity.User;

/**
 * @author ：shitao.Chen
 * @date：2017年8月7日下午2:19:59
 * @className：UserDao
 * TODO 用户数据库接口
 */
public interface UserDao extends BaseDao{
	
	/**
	 * 
	 * 通过用户名获取用户
	 * @param username
	 * @return User
	 */
	public User getUserByName(String username);
	
	/**
	 * 
	 * 通过用户名获取用户的权限
	 * @param username
	 * @return collection<String>  用户权限的集合
	 */
	public Collection<String> queryUserPermission(String username);
	
	/**
	 * 用户注册
	 * 2017年8月17日
	 * @param user
	 * author：shitao.Chen
	 */
	public void registerUser(User user);
}
