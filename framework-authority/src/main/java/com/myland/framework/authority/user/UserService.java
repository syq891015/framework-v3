package com.myland.framework.authority.user;

import com.github.pagehelper.PageInfo;
import com.myland.framework.common.base.BaseService;
import com.myland.framework.authority.po.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
public interface UserService extends BaseService<User> {

	@Override
	default void save(User user) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(User user) {

	}

	@Override
	default User getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<User> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<User> getAll() {
		return null;
	}

	Set<String> getUserPermissions(Long userId);

	User getByAccount(String username);
}
