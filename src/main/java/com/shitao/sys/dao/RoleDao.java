package com.shitao.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shitao.common.persistence.dao.BaseDao;
import com.shitao.sys.entity.Role;


public interface RoleDao extends BaseDao<Role>{

	/**
	 * 获取所有角色
	 */
	public List<Role> getAllRole();
	
	public void updateRolePermission(@Param(value="roleid")String roleid,@Param(value="permissions")String[] permissions);
	
}
