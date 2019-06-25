package com.myland.framework.logging.dao;

import com.myland.framework.logging.po.UserLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户操作日志
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-06-30 22:22:53
 */
@Repository("userLogDao")
public interface UserLogDao {
	void insert(UserLog userLog);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(UserLog userLog);

	UserLog selectByPrimaryKey(Long id);

	List<UserLog> selectList(Map<String, Object> map);

	List<UserLog> selectAll();
}
