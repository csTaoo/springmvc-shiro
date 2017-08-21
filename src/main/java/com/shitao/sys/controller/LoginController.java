package com.shitao.sys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shitao.sys.entity.User;

@Controller
public class LoginController {
	
	//请求登录页面
	@RequestMapping(value="/user/loginPage",method=RequestMethod.GET)
	public String loginPage()
	{
		return "login";
	}
	
	
	//注册页面
	@RequestMapping(value="/user/registerPage")
	public String loginFail(User user)
	{
		return "register";
	}
	
	
	//登录成功后访问此页面
	@RequestMapping(value="/user/welcome")
	public String welcome()
	{
		return "welcome";
	}
	
	
}
