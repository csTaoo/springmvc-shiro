package com.shitao.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shitao.common.utils.StringUtils;
import com.shitao.sys.entity.User;
import com.shitao.sys.security.password.EncryptionPassword;
import com.shitao.sys.service.UserService;



@Controller
@RequestMapping(value="/sys/user")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="register")
	public String registerUser(User user,HttpServletRequest request,HttpServletResponse reponse,Model model)
	{
		
		try
		{
			user.setId(StringUtils.generalUUID());
			String password = user.getPassword();
			if(StringUtils.isBlank(password))
			{
				user.setPassword(EncryptionPassword.encryptionMD5(password));
			}
			
			userService.registerUser(user);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("mes", "系统错误，请稍候重试");
			return "redirect:/shitao/register";
		}
		
		return "redirect:/shitao/index";
		
	}
	
	
	@RequestMapping(value="userIndex")
	public String userIndex(HttpServletRequest res,HttpServletResponse re,ModelMap model)
	{
		
		List<User> users = userService.getAllUser();
		
		model.addAttribute("users", users);
		return "modules/sys/userIndex";
	}
	
	
}