package com.shitao.sys.dao;

import java.util.List;

import com.shitao.common.persistence.dao.BaseDao;
import com.shitao.sys.entity.Permission;

public interface PermissionDao extends BaseDao<Permission>{

	List<Permission> getAllPermisson();
}
