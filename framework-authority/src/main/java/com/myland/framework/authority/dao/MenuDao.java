package com.myland.framework.authority.dao;

import com.myland.framework.authority.po.Menu;
import com.myland.framework.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 菜单
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Repository("menuDao")
public interface MenuDao extends BaseDao<Menu> {

	/**
	 * 根据用户ID获得经授权的权限集合
	 *
	 * @param userId 用户ID
	 * @return 权限集合
	 */
	List<String> selectPermsByUserId(Long userId);

	/**
	 * 根据父菜单ID获得所有子菜单
	 *
	 * @param pMenuId 父菜单ID
	 * @return 所有子菜单，只包括孩子，不包括孙子等
	 */
	List<Menu> selectChildren(Long pMenuId);

	/**
	 * 查询用户有访问权限的子菜单
	 * @param paramMap userId, pMenuId
	 * @return 限制菜单集合，只包括孩子，不包括孙子等
	 */
	List<Menu> selectPermissionChildren(Map<String, Long> paramMap);

	/**
	 * 根据父菜单ID获得所有子菜单，只包括树干，不包括叶子
	 *
	 * @param pMenuId 父菜单ID
	 * @return 所有子菜单，只包括孩子，不包括孙子等
	 */
	List<Menu> selectTrunkChildren(Long pMenuId);
}
