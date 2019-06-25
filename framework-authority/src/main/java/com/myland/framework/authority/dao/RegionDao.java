package com.myland.framework.authority.dao;

import com.myland.framework.authority.po.Region;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 行政区划表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-06-20 09:45:20
 */
@Repository("regionDao")
public interface RegionDao {

	void insert(Region region);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(Region region);

	Region selectByPrimaryKey(Long id);

	List<Region> selectList(Map<String, Object> map);

	List<Region> selectAll();

	List<Region> selectChildren(int pid);
}
