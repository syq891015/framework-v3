package com.myland.framework.authority.dao;

import com.myland.framework.authority.po.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Repository("roleDao")
public interface RoleDao {

	void insert(Role role);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(Role role);

	Role selectByPrimaryKey(Long id);

	List<Role> selectList(Map<String, Object> map);

	List<Role> selectAll();

	/**
	 * 根据角色ID获得该角色下的菜单ID集合
	 *
	 * @param roleId 角色ID
	 * @return 该角色下的菜单ID集合
	 */
	List<Long> selectMenuIdListByRoleId(Long roleId);

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
}
