package com.shitao.sys.entity;

import java.util.List;

public class Role extends BaseEntity {

	private List<Permission> permissions;

	public Role() {
		super();
	}

	public Role(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions
	 *            the permissions to set
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
