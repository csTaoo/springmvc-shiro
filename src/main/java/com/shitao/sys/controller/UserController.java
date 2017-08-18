package com.shitao.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shitao.common.utils.StringUtils;
import com.shitao.sys.entity.User;
import com.shitao.sys.service.UserService;



@Controller
@RequestMapping(value="/user")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="register")
	public void registerUser(User user,HttpServletRequest request,HttpServletResponse reponse,Model model)
	{
		
		try
		{
			user.setId(StringUtils.generalUUID());
			userService.registerUser(user);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}