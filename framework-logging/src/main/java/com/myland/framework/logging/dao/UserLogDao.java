package com.myland.framework.logging.dao;

import com.myland.framework.common.base.BaseDao;
import com.myland.framework.logging.po.UserLog;
import org.springframework.stereotype.Repository;

/**
 * 用户操作日志
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-06-30 22:22:53
 */
@Repository("userLogDao")
public interface UserLogDao extends BaseDao<UserLog> {
}
