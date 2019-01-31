package com.myland.framework.authority.menu;

import com.github.pagehelper.PageInfo;
import com.myland.framework.common.base.BaseService;
import com.myland.framework.authority.po.Menu;

import java.util.List;
import java.util.Map;

/**
 * 菜单
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
public interface MenuService extends BaseService<Menu> {

	@Override
	default void save(Menu menu) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(Menu menu) {

	}

	@Override
	default Menu getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<Menu> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<Menu> getAll() {
		return null;
	}
}
