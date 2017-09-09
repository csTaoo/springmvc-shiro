/**
 * 
 */
package com.shitao.sys.entity;

/**
 * @author ：shitao.Chen
 * @date：2017年8月7日下午2:23:41
 * @className：User TODO
 */
public class User extends BaseEntity {

	private String password;// 密码
	private String realname;// 真实姓名
	private short status;// 账号状态0启用 1禁用
	private Role role;// 用户角色

	public User() {
		super();
	}

	public User(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
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
	 * @param realname
	 *            the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the status
	 */
	public short getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(short status) {
		this.status = status;
	}

}
