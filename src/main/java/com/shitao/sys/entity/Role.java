package com.shitao.sys.entity;

import java.util.Set;

public class Role extends BaseEntity{
	
	private Set<Permission> permissions;
	
	/**
	 * @return the permissions
	 */
	public Set<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	

}
