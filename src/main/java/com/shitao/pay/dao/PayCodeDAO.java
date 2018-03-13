package com.shitao.pay.dao;

import com.shitao.common.persistence.dao.NotSysBaseDAO;
import com.shitao.pay.entity.PayCode;

public interface PayCodeDAO extends NotSysBaseDAO<PayCode>{
	
	PayCode checkActive();
	
}
