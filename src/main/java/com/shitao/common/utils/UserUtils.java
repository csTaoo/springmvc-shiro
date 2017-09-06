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

}
