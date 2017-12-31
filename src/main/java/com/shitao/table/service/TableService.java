package com.shitao.table.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shitao.table.dao.TableDAO;
import com.shitao.table.entity.Table;

@Service
public class TableService {
	
	@Autowired
	private TableDAO tableDao;
	
	
	public void save(Table table)
	{
		tableDao.save(table);
	}
	
	
	public void update(Table table)
	{
		tableDao.update(table);
	}
	
	public Table get(String id)
	{
		return tableDao.get(id);
	}
	
	public List<Table> list()
	{
		return tableDao.list();
	}
	
	public String getlastestNumber()
	{
		return tableDao.getlastestNumber();
	}
	
	public Table getTableByNumber(int num)
	{
		return tableDao.getTableByNumber(num);
	}
	
	public void delete(Table table)
	{
		tableDao.delete(table);
	}

}
