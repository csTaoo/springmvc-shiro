/**
 * 
 */
package com.shitao.sys.entity;

/**
 * @author ：shitao.Chen
 * @date：2017年8月7日下午2:23:41
 * @className：User
 * TODO
 */
public class User extends BaseEntity{
	
	private String password;//密码
	private String realname;//真实姓名
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	

}
