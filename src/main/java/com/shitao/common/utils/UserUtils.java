package com.shitao.common.utils;

import org.apache.shiro.SecurityUtils;
import com.shitao.sys.entity.User;

public class UserUtils {
	
	public static String getCurrentUserName() {

		User user = (User) SecurityUtils.getSubject().getPrincipal();

		if (user == null) {
			return "暂未登录";
		} else {

			return user.getName();
		}
	}
	
	
	public static String getCurrentUserId() {

		User user = (User) SecurityUtils.getSubject().getPrincipal();

		if (user == null) {
			return "暂未登录";
		} else {

			return user.getId();
		}
	}
	public static User gerCurrentUser()
	{
		return (User) SecurityUtils.getSubject().getPrincipal();
	}
	
}
