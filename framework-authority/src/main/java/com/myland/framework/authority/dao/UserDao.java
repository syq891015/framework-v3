package com.myland.framework.authority.dao;

import com.myland.framework.authority.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Repository("userDao")
public interface UserDao {

	void insert(User user);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(User User);

	User selectByPrimaryKey(Long id);

	List<User> selectList(Map<String, Object> map);

	List<User> selectAll();

	/**
	 * 根据账号查询用户信息
	 * @param username 账号名称
	 * @return 用户信息
	 */
	User selectByAccount(String username);

	/**
	 * 获得某个用户下的角色ID集合
	 *
	 * @param userId 用户ID
	 * @return 角色ID集合
	 */
	List<Long> selectRoleIdListByUserId(Long userId);

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
