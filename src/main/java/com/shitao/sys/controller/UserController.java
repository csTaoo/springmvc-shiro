package com.shitao.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shitao.common.utils.StringUtils;
import com.shitao.sys.entity.User;
import com.shitao.sys.security.password.EncryptionPassword;
import com.shitao.sys.service.SystemService;

@Controller
@RequestMapping(value = "/sys/user")
public class UserController {
	@Autowired
	private SystemService systemService;


	@RequestMapping(value = "register")
	public String registerUser(User user, HttpServletRequest request,
			HttpServletResponse reponse, Model model) {

		try {
			user.setId(StringUtils.generalUUID());
			String password = user.getPassword();
			if (StringUtils.isBlank(password)) {
				user.setPassword(EncryptionPassword.encryptionMD5(password));
			}

			systemService.registerUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mes", "系统错误，请稍候重试");
			return "redirect:/shitao/register";
		}

		return "redirect:/shitao/index";

	}

	@RequestMapping(value = "userIndex")
	public String userIndex(HttpServletRequest res, HttpServletResponse re,
			ModelMap model) {

		List<User> users = systemService.getAllUser();

		model.addAttribute("users", users);
		return "modules/sys/userIndex";
	}

	@RequestMapping(value = "modifyUser")
	public String modifyUser(HttpServletRequest res,
			@RequestParam(required = false) String id, Model model) {
		if (StringUtils.isBlank(id)) {
			User user = systemService.getUser(id);
			model.addAttribute("user", user);
		}
		model.addAttribute("roles", systemService.getAllRole());
		return "modules/sys/modifyUser";
	}

	@RequestMapping(value="updateUser")
	public String updateUser(@RequestParam(required=true) String roleid,User user,HttpServletRequest req,Model model,RedirectAttributes redirectAttr)
	{
		try 
		{
			systemService.update(user);
			systemService.updateUserRole(user.getId(),roleid);
		} catch (Exception e) {
			e.printStackTrace();
			return "modules/error/error.jsp";
		}
		
		redirectAttr.addFlashAttribute("message", "更改用户信息成功");
		return "redirect:/sys/user/userIndex";
	}
}