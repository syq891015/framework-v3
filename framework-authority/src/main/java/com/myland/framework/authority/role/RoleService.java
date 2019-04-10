package com.myland.framework.authority.role;

import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.po.Role;
import com.myland.framework.common.base.BaseService;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

	/**
	 * 根据角色ID获得该角色下的菜单ID集合
	 *
	 * @param roleId 角色ID
	 * @return 该角色下的菜单ID集合
	 */
	List<Long> getMenuIdListByRoleId(Long roleId);

	/**
	 * 删除角色菜单关系
	 *
	 * @param roleId 角色ID
	 */
	void delMenuRelationShip(Long roleId);

	/**
	 * 保存角色菜单关系
	 *
	 * @param paramMap { roleId: 1, menuIdList: [] }
	 */
	void insertMenuRelationShip(Map<String, Object> paramMap);

	/**
	 * 角色绑定菜单
	 * @param roleId  角色ID
	 * @param menuIds 菜单ID集合
	 */
	void boundMenu(Long roleId, Set<Long> menuIds);
}
