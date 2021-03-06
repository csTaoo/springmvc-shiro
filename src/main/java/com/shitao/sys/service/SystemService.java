package com.shitao.sys.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shitao.sys.entity.Func;
import com.shitao.sys.entity.Permission;
import com.shitao.sys.entity.Role;
import com.shitao.sys.entity.User;

/**
 * 门面模式，只为外部提供所有有关系统操作的接口
 * 不参与业务逻辑
 * @author ：shitao.Chen
 * @date：2017年9月9日下午8:36:23
 * @className：SystemService
 * TODO
 */
@Transactional
@Service
public class SystemService {
	
	@Autowired
	private BusinessService businessService;
	
	/**
	 * 获得功能列表
	 * 2017年9月9日
	 * @return
	 * author：shitao.Chen
	 */
	public List<Func> getAllFunc(String name)
	{
		return businessService.getAllFunc(name);
	}
	
	/**
	 * 获得权限列表
	 * 2017年9月9日
	 * @return
	 * author：shitao.Chen
	 */
	public List<Permission> getAllPermission()
	{
		return businessService.getAllPermission();
	}
	
	/**
	 * 获得角色列表
	 * 2017年9月9日
	 * @return
	 * author：shitao.Chen
	 */
	public List<Role> getAllRole(String rolename)
	{
		return businessService.getAllRole(rolename);
	}
	/**
	 * 根据id获得角色
	 * 2017年9月9日
	 * @param id
	 * @return
	 * author：shitao.Chen
	 */
	public Role getRole(String id)
	{
		return businessService.getRole(id);
	}
	/**
	 * 更新角色-权限信息
	 * 2017年9月9日
	 * @param roleid
	 * @param permissions
	 * author：shitao.Chen
	 */
	public void updateRolePermission(Role role)
	{
		businessService.updateRolePermission(role);
	}
	
	/**
	 * 更新角色-权限信息
	 * 2017年9月9日
	 * @param roleid
	 * @param permissions
	 * author：shitao.Chen
	 */
	public void addRolePermission(Role role)
	{
		businessService.addRolePermission(role);
	}
	/**
	 * 根据用户名获得用户
	 * 2017年9月9日
	 * @param username
	 * @return
	 * author：shitao.Chen
	 */
	public User getUserByName(String username)
	{
		return businessService.getUserByName(username);
	}
	
	/**
	 * 用户注册
	 * 2017年9月9日
	 * @param user
	 * @throws Exception
	 * author：shitao.Chen
	 */
	public void registerUser(User user,String roleid) throws Exception
	{
		businessService.registerUser(user);
		businessService.addUserRole(user.getId(), roleid);
	}
	
	/**
	 * 获得用户列表
	 * 2017年9月9日
	 * @return
	 * author：shitao.Chen
	 */
	public List<User> getAllUser(String username)
	{
		return businessService.getAllUser(username);
	}
	
	/**
	 * 根据id获得用户
	 * 2017年9月9日
	 * @param id
	 * @return
	 * author：shitao.Chen
	 */
	public User getUser(String id)
	{
		return businessService.getUser(id);
	}
	
	/**
	 * 更新用户信息
	 * 2017年9月9日
	 * @param user
	 * @throws Exception
	 * author：shitao.Chen
	 */
	public void update(User user,String roleid) throws Exception
	{
		businessService.update(user);
		businessService.updateUserRole(user.getId(), roleid);
	}
	
	public void update(User user) throws Exception
	{
		businessService.update(user);
	}
	/**
	 * 更新用户-角色
	 * 2017年9月9日
	 * @param userid
	 * @param roleid
	 * author：shitao.Chen
	 */
	public void updateUserRole(String userid,String roleid)
	{
		businessService.updateUserRole(userid, roleid);
	}
	/**
	 * 查询用户权限
	 * 2017年9月9日
	 * @param username
	 * @return
	 * author：shitao.Chen
	 */
	public Collection<String> queryUserPermission(String username)
	{
		return businessService.queryUserPermission(username);
	}
	
	public Func getFunc(String id)
	{
		return businessService.getFunc(id);
	}
	
	
	public void startStopFunc(String id)
	{
		businessService.startStopFunc(id);
	}
	
	public void delRole(Role role)
	{
		businessService.delRole(role);
	}
	
	public void delUser(String id)
	{
		businessService.delUser(id);
	}
	
}	
