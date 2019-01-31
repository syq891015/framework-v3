package com.myland.framework.authority.dao;

import com.myland.framework.common.base.BaseDao;
import com.myland.framework.authority.po.Role;
import org.springframework.stereotype.Repository;

/**
 * 角色
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Repository("roleDao")
public interface RoleDao extends BaseDao<Role> {

}
