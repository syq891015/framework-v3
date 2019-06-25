package com.myland.framework.authority.region;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.dao.RegionDao;
import com.myland.framework.authority.po.Region;
import com.myland.framework.authority.po.RegionTree;
import com.myland.framework.common.utils.CachedBeanCopier;
import com.myland.framework.common.utils.validator.Assert;
import com.myland.framework.datasource.config.redis.RedisCacheInitService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.myland.framework.authority.consts.CacheConstants.REGION_CHILDREN;
import static com.myland.framework.authority.consts.CacheConstants.REGION_MAP;

/**
 * 行政区划表Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-06-20 09:45:20
 */
@Service("regionService")
public class RegionServiceImpl extends RedisCacheInitService implements RegionService {
	@Resource
	private RegionDao regionDao;

	@Override
	public Region getObjById(Long id) {
		return regionDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Region> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(regionDao.selectList(map));
	}

	@Override
	public List<Region> getAll() {
		return regionDao.selectAll();
	}

	@Override
	public List<RegionTree> getCascadeRegion(int pid) {
		List<Region> children = getChildren(pid);

		List<RegionTree> regionTrees = CachedBeanCopier.copy(children, RegionTree.class);

		for (RegionTree childRegion : regionTrees) {
			List<RegionTree> grandson = getCascadeRegion(childRegion.getId());
			if (grandson.size() > 0) {
				childRegion.setChildren(grandson);
			}
		}
		return regionTrees;
	}

	@Override
	public String getName(Integer id) {
		Assert.isNotNull(id, "###获得区划名称的ID为空");
		Region region = redisUtils.hGet(REGION_MAP, id.toString(), Region.class);
		return region != null ? region.getName() : "";
	}

	@Override
	public List<Region> getChildren(int pid) {
		List<Region> regions = redisUtils.hGet(REGION_CHILDREN, String.valueOf(pid), List.class);
		return regions == null ? new ArrayList<>(0) : regions;
	}

	@Override
	public void save(Region region) {
		regionDao.insert(region);
	}

	@Override
	public void update(Region region) {
		regionDao.updateByPrimaryKeySelective(region);
	}

	@Override
	public void delete(Long id) {
		regionDao.deleteByPrimaryKey(id);
	}

	@Override
	public String getName() {
		return "区划缓存";
	}

	@SuppressWarnings("unchecked")
	@Override
	@PostConstruct
	public void inputCache() {
		List<Region> regionList = getAll();

		// {id: Region}
		Map<String, Object> regionMap = new HashMap<>(regionList.size());
		for (Region region : regionList) {
			regionMap.put(region.getId().toString(), region);
		}
		redisUtils.hmSet(REGION_MAP, regionMap);

		// {100000: [{北京市}, {天津市}...], ...}
		Map<String, Object> childrendMap = new HashMap<>();
		for (Region region : regionList) {
			Integer pid = region.getPid();
			List<Region> children = (List<Region>) childrendMap.get(pid.toString());
			if (children == null) {
				children = new ArrayList<>(40);
				childrendMap.put(pid.toString(), children);
			}
			children.add(region);
		}
		redisUtils.hmSet(REGION_CHILDREN, childrendMap);
	}
}
