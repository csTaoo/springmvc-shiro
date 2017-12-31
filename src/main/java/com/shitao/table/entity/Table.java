package com.shitao.table.entity;

import com.shitao.common.entity.AbstractEntity;

public class Table extends AbstractEntity{
	
	private int table_num;
	private String table_vrCode;
	private short table_status;
	/**
	 * @return the table_num
	 */
	public int getTable_num() {
		return table_num;
	}
	/**
	 * @param table_num the table_num to set
	 */
	public void setTable_num(int table_num) {
		this.table_num = table_num;
	}
	/**
	 * @return the table_vrCode
	 */
	public String getTable_vrCode() {
		return table_vrCode;
	}
	/**
	 * @param table_vrCode the table_vrCode to set
	 */
	public void setTable_vrCode(String table_vrCode) {
		this.table_vrCode = table_vrCode;
	}
	/**
	 * @return the table_status
	 */
	public short getTable_status() {
		return table_status;
	}
	/**
	 * @param table_status the table_status to set
	 */
	public void setTable_status(short table_status) {
		this.table_status = table_status;
	}

}
