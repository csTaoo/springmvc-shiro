package com.shitao.table.dao;

import com.shitao.common.persistence.dao.NotSysBaseDAO;
import com.shitao.table.entity.Table;

public interface TableDAO extends NotSysBaseDAO<Table>{

	
	String getlastestNumber();
	
	Table getTableByNumber(int num);
	
	
}
