/**
 * 
 */
package com.shitao.sys.security;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shitao.sys.entity.User;
import com.shitao.sys.service.UserService;

/**
 * @author ：shitao.Chen
 * @date：2017年8月7日上午10:41:02
 * @className：MyShiroRealm TODO
 */
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	// 获取授权信息 permission
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		// 获取登录的用户名
		String username = (String) principals.fromRealm(getName()).iterator()
				.next();

		if (username != null) {
			// 根据用户名查询授权
			Collection<String> permissions = userService
					.queryUserPermission(username);
			if (permissions != null && !permissions.isEmpty()) {

				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				for (String str : permissions) {
					info.addStringPermission(str);
				}

				return info;

			}

		}

		return null;
	}

	// 获取验证回调信息 token
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		String username = authcToken.getUsername();

		if (username != null) {
			User user = userService.getUserByName(username);

			if (user != null) {
				return new SimpleAuthenticationInfo(user.getUsername(),
						user.getPassword(), getName());
			}
		}
		return null;
	}
	
}
