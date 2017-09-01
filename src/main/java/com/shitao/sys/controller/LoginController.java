package com.shitao.sys.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shitao.sys.entity.User;

@Controller
public class LoginController {
	
	//请求登录页面
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage()
	{
		return "modules/sys/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse re,Model model)
	{
		
		String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		
		return "modules/sys/login";
	}
	
	//注册页面
	@RequestMapping(value="/registe",method=RequestMethod.GET)
	public String registe(User user)
	{
		return "modules/sys/register";
	}
	
	
	
	//登录成功后访问此页面
	@RequestMapping(value="index")
	public String index(HttpServletRequest req ,HttpServletResponse re,Model model)
	{
		
		//获得当前用户
		Subject subject = SecurityUtils.getSubject();
		String username = ((User)subject.getPrincipal()).getUsername();
		model.addAttribute("username",username);
		return "modules/sys/index";
	}
	
	
	@RequestMapping(value="logout")
	public String logout()
	{
		SecurityUtils.getSubject().logout();
		return "welcome";
	}
}
