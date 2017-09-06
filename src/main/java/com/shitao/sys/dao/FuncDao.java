package com.shitao.sys.dao;

import java.util.List;

import com.shitao.common.persistence.dao.BaseDao;
import com.shitao.sys.entity.Func;


/**
 * 
 * @author ：shitao.Chen
 * @date：2017年9月6日上午9:46:43
 * @className：FuncDao
 * TODO  功能接口，主要用户管理系统的所有功能
 * 如功能停用，功能启用
 */
public interface FuncDao extends BaseDao{
	
	List<Func> getAllFunc();

}
