package com.myland.framework.authority.roleMenu;

import com.github.pagehelper.PageInfo;
import com.myland.framework.common.base.BaseService;
import com.myland.framework.authority.po.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * 角色菜单表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
public interface RoleMenuService extends BaseService<RoleMenu> {

	@Override
	default void save(RoleMenu roleMenu) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(RoleMenu roleMenu) {

	}

	@Override
	default RoleMenu getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<RoleMenu> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<RoleMenu> getAll() {
		return null;
	}
}
