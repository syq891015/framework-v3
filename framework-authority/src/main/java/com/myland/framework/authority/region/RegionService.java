package com.myland.framework.authority.region;

import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.po.Region;
import com.myland.framework.authority.po.RegionTree;

import java.util.List;
import java.util.Map;

/**
 * 行政区划表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-06-20 09:45:20
 */
public interface RegionService {

	List<Region> getChildren(int pid);

	void save(Region region);

	void delete(Long id);

	void update(Region region);

	Region getObjById(Long id);

	PageInfo<Region> getList4Page(Map<String, Object> map);

	List<Region> getAll();

	List<RegionTree> getCascadeRegion(int pid);

	String getName(Integer id);
}
