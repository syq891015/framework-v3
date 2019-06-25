package com.myland.framework.authority.dao;

import com.myland.framework.authority.po.Region;
import com.myland.framework.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 行政区划表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-06-20 09:45:20
 */
@Repository("regionDao")
public interface RegionDao extends BaseDao<Region> {

	List<Region> selectChildren(int pid);
}
