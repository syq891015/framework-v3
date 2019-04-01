package com.myland.framework.authority.userRole;

import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.po.UserRole;
import com.myland.framework.common.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 用户角色表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
public interface UserRoleService extends BaseService<UserRole> {

	@Override
	default void save(UserRole userRole) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(UserRole userRole) {

	}

	@Override
	default UserRole getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<UserRole> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<UserRole> getAll() {
		return null;
	}
}
