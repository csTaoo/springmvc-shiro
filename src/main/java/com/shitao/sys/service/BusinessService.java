package com.shitao.sys.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shitao.sys.dao.FuncDao;
import com.shitao.sys.dao.PermissionDao;
import com.shitao.sys.dao.RoleDao;
import com.shitao.sys.dao.UserDao;
import com.shitao.sys.entity.Func;
import com.shitao.sys.entity.Permission;
import com.shitao.sys.entity.Role;
import com.shitao.sys.entity.User;

/**
 * 系统业务逻辑类
 * @author ：shitao.Chen
 * @date：2017年9月9日下午8:39:31
 * @className：BusinessService
 * 
 */
@Service
@Transactional
public class BusinessService {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FuncDao funcDao;
	
	@Autowired
	private PermissionDao pDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 获取功能列表
	 * 2017年9月9日
	 * @return
	 * author：shitao.Chen
	 */
	public List<Func> getAllFunc() {
		List<Func> funcs = funcDao.getAllFunc();
		return (funcs.isEmpty()) ? funcs = new ArrayList<Func>() : funcs;
	}
	
	/**
	 * 获取所有权限
	 * 2017年9月9日
	 * @return
	 * author：shitao.Chen
	 */
	public List<Permission> getAllPermission() {
		List<Permission> permissions = pDao.getAllPermisson();
		return (permissions.isEmpty()) ? permissions = new ArrayList<Permission>()
				: permissions;
	}
	
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
	
	/**
	 * 根据id获取角色
	 * 2017年9月9日
	 * @param id
	 * @return
	 * author：shitao.Chen
	 */
	public Role getRole(String id)
	{
		Role role = roleDao.get(id);
		return role;
	}
	
	/**
	 * 更新角色-权限信息
	 * 2017年9月9日
	 * @param roleid
	 * @param permissions
	 * author：shitao.Chen
	 * @throws Exception 
	 */
	public void updateRolePermission(Role role)
	{
		roleDao.update(role);
		roleDao.deleteRolePermission(role);
		roleDao.updateRolePermission(role);
	}
	
	/**
	 * 根据用户名，获取用户
	 * 2017年9月9日
	 * @param username
	 * @return
	 * author：shitao.Chen
	 */
	@Transactional(readOnly = false)
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(username);
	}

	
	/**
	 * 用户注册
	 * 2017年9月9日
	 * @param user
	 * @throws Exception
	 * author：shitao.Chen
	 */
	public void registerUser(User user) throws Exception {
		// TODO Auto-generated method stub

		userDao.registerUser(user);
	}

	@Transactional
	public List<User> getAllUser() {
		List<User> users = userDao.getAllUser();
		return (users.isEmpty()) ? users = new ArrayList<User>() : users;

	}
	
	public User getUser(String id)
	{
		return userDao.get(id);
	}
	
	/**
	 * 用户信息更新
	 * @throws Exception 
	 * 
	 */
	public void update(User user) throws Exception
	{
		userDao.update(user);
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
		userDao.updateUserRole(userid, roleid);
	}
	
	public Collection<String> queryUserPermission(String username)
	{
		return userDao.queryUserPermission(username);
	}
}
