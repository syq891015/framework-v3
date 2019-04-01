package com.myland.framework.authority.dao;

import com.myland.framework.authority.po.UserRole;
import com.myland.framework.common.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * 用户角色表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Repository("userRoleDao")
public interface UserRoleDao extends BaseDao<UserRole> {

}
