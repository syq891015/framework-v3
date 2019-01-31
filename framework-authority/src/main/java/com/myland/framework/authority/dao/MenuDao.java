package com.myland.framework.authority.dao;

import com.myland.framework.common.base.BaseDao;
import com.myland.framework.authority.po.Menu;
import org.springframework.stereotype.Repository;

/**
 * 菜单
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Repository("menuDao")
public interface MenuDao extends BaseDao<Menu> {

}
