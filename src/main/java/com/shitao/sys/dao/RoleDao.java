package com.shitao.sys.dao;

import java.util.List;

import com.shitao.common.persistence.dao.BaseDao;
import com.shitao.sys.entity.Role;


public interface RoleDao extends BaseDao<Role>{

	/**
	 * 获取所有角色
	 */
	public List<Role> getAllRole();
	
}
