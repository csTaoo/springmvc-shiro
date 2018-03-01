package com.shitao.sys.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shitao.common.utils.StringUtils;
import com.shitao.common.utils.UserUtils;
import com.shitao.sys.entity.User;
import com.shitao.sys.security.password.EncryptionPassword;
import com.shitao.sys.service.SystemService;

@Controller
@RequestMapping(value = "/sys/user")
public class UserController {
	@Autowired
	private SystemService systemService;


	@RequestMapping(value = "register")
	@ResponseBody
	public String registerUser(User user, HttpServletRequest request,
			HttpServletResponse reponse, Model model) {

		try {
			user.setId(StringUtils.generalUUID());
			String password = user.getPassword();
			if (StringUtils.isBlank(password)) {
				user.setPassword(EncryptionPassword.encryptionMD5(password));
			}

			systemService.registerUser(user,"2");
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@RequestMapping(value = "userIndex")
	public String userIndex(HttpServletRequest res, HttpServletResponse re,
			ModelMap model) {
		String username =res.getParameter("username");
		List<User> users = systemService.getAllUser(username);

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
		model.addAttribute("roles", systemService.getAllRole(""));
		return "modules/sys/modifyUser";
	}

	@RequestMapping(value="updateUser")
	public String updateUser(@RequestParam(required=false) String roleid,User user,HttpServletRequest req,Model model,RedirectAttributes redirectAttr)
	{	
		String save = req.getParameter("save");
		try{
		//保存
		if(save != null)
		{
			user.setId(StringUtils.generalUUID());
			String password = user.getPassword();
			if (StringUtils.isBlank(password)) {
				user.setPassword(EncryptionPassword.encryptionMD5(password));
			}

			systemService.registerUser(user,roleid);
		}
		//更新
		else
		{
			systemService.update(user,roleid);
			systemService.updateUserRole(user.getId(),roleid);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			return "modules/error/error.jsp";
		}
		redirectAttr.addFlashAttribute("message", "更改用户信息成功");
		return "redirect:/sys/user/userIndex";
	}
	
	@RequestMapping(value="editpassword")
	public String editPassword()
	{
		return "modules/sys/editpassword";
	}
	
	@RequestMapping(value="updatepassword")
	@ResponseBody
	public void updatePassword(HttpServletRequest req,HttpServletResponse res)
	{
		String oldpwd = req.getParameter("oldpwd");
		String confirmpwd = req.getParameter("confirmpwd");
		
		User user = UserUtils.gerCurrentUser();
		String cryptopwd = user.getPassword();
		String pwd = cryptopwd.substring(16);
		ByteSource bs = ByteSource.Util.bytes(cryptopwd.substring(0, 16));
		Sha1Hash sha1 = new Sha1Hash(oldpwd,bs,2);
		
		try
		{
			res.setContentType("text/plain");
			PrintWriter writer = res.getWriter();
			if(!sha1.toHex().equals(pwd))
			{
				writer.write("请确认原密码");
				return;
			}else
			{
				user.setPassword(EncryptionPassword.encryptionMD5(confirmpwd));
				systemService.update(user);
				writer.write("success");
				return;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletRequest req,HttpServletResponse res)
	{
		String userid = req.getParameter("userId");
		systemService.delUser(userid);
	}
}