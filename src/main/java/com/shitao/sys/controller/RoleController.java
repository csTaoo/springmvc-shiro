package com.shitao.sys.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.shitao.common.utils.StringUtils;
import com.shitao.sys.entity.Permission;
import com.shitao.sys.entity.Role;
import com.shitao.sys.service.SystemService;

@Controller
@RequestMapping(value = "/sys/role")
public class RoleController {

	@Autowired
	private SystemService systemService;

	@RequestMapping(value = "roleindex")
	public String roleindex(HttpServletRequest req, HttpServletResponse re,
			Model model) {
		String rolename = req.getParameter("rolename");
		List<Role> roles = systemService.getAllRole(rolename);

		model.addAttribute("roles", roles);
		return "modules/sys/roleindex";
	}

	@RequestMapping(value = ("getRolePermissionJson"))
	@ResponseBody
	public void getRolePermissionJson(@RequestParam(required = true) String id,
			HttpServletResponse re) {
		Role role = systemService.getRole(id);
		re.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter write = re.getWriter();
			write.write(JSON.toJSONString(role));
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * 
	 * 2017年9月7日
	 * 
	 * @param roleid
	 * @param re
	 */
	@RequestMapping(value = "getPermissions")
	@ResponseBody
	public void getPermissions(HttpServletResponse re) {
		// 所有权限
		List<Permission> permissions = systemService.getAllPermission();
		

		// 区分已有和没有的权限
		Map<String, List<Permission>> result = new HashMap<String, List<Permission>>();

		result.put("N", permissions);
		re.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter write = re.getWriter();
			write.write(JSON.toJSONString(result));
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "getFiltedPermission")
	@ResponseBody
	public void getFiltedPermission(
			@RequestParam(required = true) String roleid, HttpServletResponse re) {
		Role role = systemService.getRole(roleid);
		// 所有权限
		List<Permission> permissions = systemService.getAllPermission();
		// 角色的权限
		List<Permission> roleP = role.getPermissions();

		// 将所有权限去除角色已有的权限
		for (int i = 0; i < roleP.size(); i++) {
			for (int j = 0; j < permissions.size(); j++) {
				if (roleP.get(i).getName().equals(permissions.get(j).getName())) {
					permissions.remove(j);
					break;
				}
			}
		}

		// 区分已有和没有的权限
		Map<String, List<Permission>> result = new HashMap<String, List<Permission>>();

		result.put("Y", roleP);
		result.put("N", permissions);
		re.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter write = re.getWriter();
			write.write(JSON.toJSONString(result));
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	@RequestMapping(value = "updateRolePermission")
	public String updateRolePermission(HttpServletRequest req,
			HttpServletResponse re, Model model,RedirectAttributes redirectAttr) {
		String roleid = req.getParameter("roleid");
		String[] permissions = req.getParameterValues("permission");

		String rolename = req.getParameter("rolename");

		Role role = new Role(roleid);
		role.setName(rolename);

		if (permissions != null) {
			List<Permission> list = new LinkedList<Permission>();
			for (String item : permissions) {
				list.add((new Permission(item)));
			}

			role.setPermissions(list);
		}
		
		systemService.updateRolePermission(role);
		redirectAttr.addFlashAttribute("message", "修改成功");
		return "redirect:/sys/role/roleindex";
	}
	
	@RequestMapping(value = "addRolePermission")
	public String addRolePermission(HttpServletRequest req,
			HttpServletResponse re, Model model,RedirectAttributes redirectAttr) {
		String roleid = StringUtils.generalUUID();
		String[] permissions = req.getParameterValues("permission");

		String rolename = req.getParameter("rolename");

		Role role = new Role(roleid);
		role.setName(rolename);

		if (permissions != null) {
			List<Permission> list = new LinkedList<Permission>();
			for (String item : permissions) {
				list.add((new Permission(item)));
			}

			role.setPermissions(list);
		}
		
		systemService.addRolePermission(role);
		redirectAttr.addFlashAttribute("message", "修改成功");
		return "redirect:/sys/role/roleindex";
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletRequest req,HttpServletResponse res)
	{
		String roleId = req.getParameter("roleId");
		Role role = new Role(roleId);
		systemService.delRole(role);
	}
}
