package com.myland.framework.authority.user;

import com.github.pagehelper.PageInfo;
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
public interface UserService {

	void save(User user);

	void delete(Long id);

	void update(User user);

	User getObjById(Long id);

	PageInfo<User> getList4Page(Map<String, Object> map);

	List<User> getAll();

	Set<String> getUserPermissions(Long userId);

	User getByAccount(String username);

	/**
	 * 获得某个用户下的角色ID集合
	 *
	 * @param userId 用户ID
	 * @return 角色ID集合
	 */
	List<Long> getRoleIdListByUserId(Long userId);

	/**
	 * 给用户绑定角色
	 *
	 * @param userId  用户ID
	 * @param roleIds 角色ID
	 */
	void boundRole(Long userId, List<Long> roleIds);

	/**
	 * 删除用户角色关系
	 *
	 * @param userId 用户ID
	 */
	void delRoleRelationShip(Long userId);

	/**
	 * 保存用户角色关系
	 *
	 * @param paramMap { userId: 1, roleIdList: [] }
	 */
	void insertRoleRelationShip(Map<String, Object> paramMap);

}
