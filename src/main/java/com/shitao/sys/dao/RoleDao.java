package com.shitao.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shitao.common.persistence.dao.BaseDao;
import com.shitao.sys.entity.Role;


public interface RoleDao extends BaseDao<Role>{

	/**
	 * 获取所有角色
	 */
	public List<Role> getAllRole(@Param(value="rolename")String rolename);
	
	/**
	 * 更新角色-权限信息
	 * 2017年9月9日
	 * @param roleid
	 * @param permissions
	 * author：shitao.Chen
	 */
	public void updateRolePermission(Role role);
	
	/**
	 * 删除角色-权限信息
	 * 2017年9月9日
	 * @param roleid
	 * author：shitao.Chen
	 */
	void deleteRolePermission(Role role);
	
	
	void addUserRole(@Param(value="userId")String userid,@Param(value="roleId")String roleid);
	
	void deleteUserRole(@Param(value="id")String id);
	
}
