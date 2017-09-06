package com.shitao.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitao.sys.dao.FuncDao;
import com.shitao.sys.entity.Func;

@Service
public class FuncService {

	@Autowired
	private FuncDao funcDao;

	public List<Func> getAllFunc() {
		List<Func> funcs = funcDao.getAllFunc();
		return (funcs.isEmpty()) ? funcs = new ArrayList<Func>() : funcs;
	}
}
