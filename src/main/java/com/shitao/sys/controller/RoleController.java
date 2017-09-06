package com.shitao.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shitao.sys.entity.Role;
import com.shitao.sys.service.RoleService;

@Controller
@RequestMapping(value="/sys/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "roleindex")
	public String roleindex(HttpServletRequest req, HttpServletResponse re,
			Model model) {
		List<Role> roles = roleService.getAllRole();

		
		model.addAttribute("roles", roles);
		return "modules/sys/roleindex";
	}
}
