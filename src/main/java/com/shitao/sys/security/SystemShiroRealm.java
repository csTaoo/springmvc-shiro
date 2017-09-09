package com.shitao.sys.security;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.shitao.common.utils.StringUtils;
import com.shitao.sys.entity.User;
import com.shitao.sys.service.SystemService;

/**
 * @author ：shitao.Chen
 * @date：2017年8月7日上午10:41:02
 * @className：MyShiroRealm TODO
 */
public class SystemShiroRealm extends AuthorizingRealm {

	@Autowired
	private SystemService systemService;
	
	public SystemShiroRealm() {
		// TODO Auto-generated constructor stub
		super();
		/**
		 * 放在构造方法中初始化，或者使用@postconstrut将方法注解
		 * @postconstrut这个方法在@service注解后生效，执行初始化动作
		 * 并且这个方法只能够注解一个方法
		 */
		initCredentialsMatcher();
		
	}
	
	// 获取授权信息 permission
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		// 获取登录的用户名
		String username = (String) principals.fromRealm(getName()).iterator()
				.next();

		if (username != null) {
			// 根据用户名查询授权
			Collection<String> permissions = systemService
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
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		String username = authcToken.getUsername();
		
		if (StringUtils.isBlank(username)) {
			User user = systemService.getUserByName(username);
			String password = user.getPassword().substring(16);
			if (user != null) {
				return new SimpleAuthenticationInfo(user ,
						password,ByteSource.Util.bytes(user.getPassword().substring(0, 16)), getName());
			}
		}
		return null;
	}
	
	//初始化密码的加密方式
	public void initCredentialsMatcher()
	{
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
		//进行加密的次数
		matcher.setHashIterations(2);
		this.setCredentialsMatcher(matcher);
		
	}
	
}
