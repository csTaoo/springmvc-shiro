package com.shitao.sys.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shitao.sys.entity.User;

@Controller
public class LoginController {
	
	//请求登录页面
	@RequestMapping(value="/user/login",method=RequestMethod.GET)
	public String loginPage()
	{
		return "login";
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse re,Model model)
	{
		
		String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		
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
