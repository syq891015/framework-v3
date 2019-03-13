package com.myland.framework.authority.dao;

import com.myland.framework.common.base.BaseDao;
import com.myland.framework.authority.po.User;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Repository("userDao")
public interface UserDao extends BaseDao<User> {

	/**
	 * 根据账号查询用户信息
	 * @param username 账号名称
	 * @return 用户信息
	 */
	User selectByAccount(String username);
}
