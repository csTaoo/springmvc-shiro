/**
 * 
 */
package com.shitao.sys.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shitao.sys.dao.UserDao;
import com.shitao.sys.entity.User;

/**
 * @author ：shitao.Chen
 * @date：2017年8月7日下午2:25:38
 * @className：UserService
 * TODO ：用户服务类
 */
@Service
public class UserService {
	
	/*
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly=false)
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(username);
	}

	public Collection<String> queryUserPermission(String username) {
		// TODO Auto-generated method stub
		return userDao.queryUserPermission(username);
	}

	public void registerUser(User user)throws Exception {
		// TODO Auto-generated method stub
		
		userDao.registerUser(user);
	}

	

}
