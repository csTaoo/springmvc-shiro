package com.shitao.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitao.sys.dao.PermissionDao;
import com.shitao.sys.entity.Permission;

@Service
public class PermissionService {

	@Autowired
	private PermissionDao pDao;

	public List<Permission> getAllPermission() {
		List<Permission> permissions = pDao.getAllPermisson();
		return (permissions.isEmpty()) ? permissions = new ArrayList<Permission>()
				: permissions;
	}

}
