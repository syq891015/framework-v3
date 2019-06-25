package com.myland.framework.logging.userLog;


import com.github.pagehelper.PageInfo;
import com.myland.framework.logging.po.UserLog;

import java.util.List;
import java.util.Map;

/**
 * 用户操作日志
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-06-30 22:22:53
 */
public interface UserLogService {
	void save(UserLog userLog);

	void delete(Long id);

	void update(UserLog userLog);

	UserLog getObjById(Long id);

	PageInfo<UserLog> getList4Page(Map<String, Object> map);

	List<UserLog> getAll();
}
