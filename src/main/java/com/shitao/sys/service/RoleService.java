package com.shitao.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shitao.sys.dao.RoleDao;
import com.shitao.sys.entity.Role;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 
	 * 2017年9月7日
	 * @return 所有角色
	 * author：shitao.Chen
	 */
	public List<Role> getAllRole() {
		List<Role> roles = roleDao.getAllRole();
		return (roles.isEmpty()) ? roles = new ArrayList<Role>() : roles;
	}
	
	public Role get(String id)
	{
		Role role = roleDao.get(id);
		return role;
	}
	
	
	public void updateRolePermission(String roleid,String[] permissions)
	{
		roleDao.updateRolePermission(roleid, permissions);
	}
	
}
