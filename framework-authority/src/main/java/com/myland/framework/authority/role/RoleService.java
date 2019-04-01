package com.myland.framework.authority.role;

import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.po.Role;
import com.myland.framework.common.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
public interface RoleService extends BaseService<Role> {

	@Override
	default void save(Role role) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(Role role) {

	}

	@Override
	default Role getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<Role> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<Role> getAll() {
		return null;
	}
}
