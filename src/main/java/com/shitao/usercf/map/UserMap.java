package com.shitao.usercf.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shitao.sys.dao.UserDao;

@Component
public class UserMap {
	
	private static UserDao userDao;
	
	public final static Map<String,Integer> USER_MAP = new HashMap<String,Integer>();
	
	@Autowired
	public void setUserDao(UserDao userDao)
	{
		UserMap.userDao = userDao;
	}
	
	public static void init()
	{
		List<String> listUserId = userDao.listUserId();
		
		int i = 1;
		for (String userid : listUserId) {
			
			USER_MAP.put(userid, i);
			i++;
		}
	}
	
	public static Integer getUserInt(String userid)
	{
		return USER_MAP.get(userid);
	}
	
}
