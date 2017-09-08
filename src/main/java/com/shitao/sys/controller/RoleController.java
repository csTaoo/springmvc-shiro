package com.shitao.sys.controller;

import java.io.PrintWriter;
import java.util.HashMap;
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

import com.alibaba.fastjson.JSON;
import com.shitao.sys.entity.Permission;
import com.shitao.sys.entity.Role;
import com.shitao.sys.service.PermissionService;
import com.shitao.sys.service.RoleService;

@Controller
@RequestMapping(value = "/sys/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService pService;

	@RequestMapping(value = "roleindex")
	public String roleindex(HttpServletRequest req, HttpServletResponse re,
			Model model) {
		List<Role> roles = roleService.getAllRole();

		model.addAttribute("roles", roles);
		return "modules/sys/roleindex";
	}

	@RequestMapping(value = ("getRolePermissionJson"))
	@ResponseBody
	public void getRolePermissionJson(@RequestParam(required = true) String id,
			HttpServletResponse re) {
		Role role = roleService.get(id);
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
	 * @param roleid
	 * @param re
	 * author：shitao.Chen
	 * 返回过滤之后的权限结果
	 */
	@RequestMapping(value = "getFiltedPermission")
	@ResponseBody
	public void getFiltedPermission(
			@RequestParam(required = true) String roleid, HttpServletResponse re) {
		Role role = roleService.get(roleid);
		//所有权限
		List<Permission> permissions = pService.getAllPermission();
		//角色的权限
		List<Permission> roleP=role.getPermissions();
		
		//将所有权限去除角色已有的权限
		for(int i=0;i<roleP.size();i++)
		{
			for(int j=0;j<permissions.size();j++)
			{
				if(roleP.get(i).getName().equals(permissions.get(j).getName()))
				{
					permissions.remove(j);
					break;
				}
			}
		}
		
		//区分已有和没有的权限
		Map<String,List<Permission>> result = new HashMap<String,List<Permission>>();
		
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
	
	@RequestMapping(value="updateRolePermission")
	public String updateRolePermission(HttpServletRequest req,HttpServletResponse re,Model model)
	{
		String roleid = req.getParameter("roleid");
		String[] permissions = req.getParameterValues("permission");
		
		roleService.updateRolePermission(roleid,permissions);
		return "";
	}
}
