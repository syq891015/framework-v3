package com.myland.framework.shiro;

import com.myland.framework.authority.consts.UserConstants;
import com.myland.framework.authority.domain.LoginUser;
import com.myland.framework.authority.po.User;
import com.myland.framework.authority.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;

/**
 * 用户校验领域
 *
 * @author SunYanQing
 */
@Configuration
@Component
public class UserRealm extends AuthorizingRealm {
	@Resource
	private UserService userService;

	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LoginUser user = (LoginUser) principals.getPrimaryPrincipal();
		Long userId = user.getId();

		// 用户权限列表
		Set<String> permsSet = userService.getUserPermissions(userId);

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		// 查询用户信息
		User user = userService.getByAccount(username);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("没有此账号");
		}

		// 密码错误
		if (!password.equals(user.getPasswd())) {
			throw new IncorrectCredentialsException("密码不正确");
		}

		// 账号锁定
		if (Objects.equals(UserConstants.LOCK_STATUS, user.getStatus())) {
			throw new LockedAccountException("账号已锁定");
		}

		// 账号删除
		if (user.isDeleted()) {
			throw new LockedAccountException("账号已删除");
		}

		LoginUser loginUser = new LoginUser();
		loginUser.setId(user.getId());
		loginUser.setAccount(user.getAccount());
		loginUser.setName(user.getName());
		loginUser.setAvatar(user.getAvatar());
		loginUser.setPhone(user.getPhone());
		loginUser.setSex(user.getSex());

		return new SimpleAuthenticationInfo(loginUser, password, getName());
	}
}
