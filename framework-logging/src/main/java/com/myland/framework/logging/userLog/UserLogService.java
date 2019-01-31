package com.myland.framework.logging.userLog;


import com.github.pagehelper.PageInfo;
import com.myland.framework.common.base.BaseService;
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
public interface UserLogService extends BaseService<UserLog> {
	@Override
	default void save(UserLog userLog) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(UserLog userLog) {

	}

	@Override
	default UserLog getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<UserLog> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<UserLog> getAll() {
		return null;
	}
}
