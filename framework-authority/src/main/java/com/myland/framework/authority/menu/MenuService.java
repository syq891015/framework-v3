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

	/**
	 * 根据用户ID获得经授权的权限集合
	 *
	 * @param userId 用户ID
	 * @return 权限集合
	 */
	List<String> getPermsByUserId(Long userId);

	/**
	 * 获得菜单树
	 *
	 * @param pMenuId 父菜单ID
	 * @return 菜单树形结构
	 */
	List<Menu> getAllTree(Long pMenuId);

	/**
	 * 根据父菜单ID获得所有子菜单
	 *
	 * @param pMenuId 父菜单ID
	 * @return 所有子菜单，只包括孩子，不包括孙子等
	 */
	List<Menu> getChildren(Long pMenuId);

	/**
	 * 根据父菜单ID获得所有子菜单，只包括树干，不包括叶子
	 *
	 * @param pMenuId 父菜单ID
	 * @return 所有子菜单，只包括孩子，不包括孙子等
	 */
	List<Menu> getTrunkChildren(Long pMenuId);

	/**
	 * 获得用户拥有的菜单树
	 *
	 * @param userId 用户ID
	 * @param pMenuId 父菜单ID
	 * @return 菜单树形结构
	 */
	List<Menu> getPermissionTree(Long userId, Long pMenuId);

	/**
	 * 获得用户有访问权限的子菜单
	 *
	 * @param userId 用户ID
	 * @param pMenuId 父菜单ID
	 * @return 有访问权限的子菜单，只包括孩子，不包括孙子等
	 */
	List<Menu> getPermissionChildren(Long userId, Long pMenuId);
}
