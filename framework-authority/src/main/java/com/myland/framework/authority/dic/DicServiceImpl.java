package com.myland.framework.authority.dic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.baseDic.BaseDicService;
import com.myland.framework.authority.consts.CacheConstants;
import com.myland.framework.authority.dao.DicDao;
import com.myland.framework.authority.po.BaseDic;
import com.myland.framework.authority.po.Dic;
import com.myland.framework.datasource.config.redis.CacheInitService;
import com.myland.framework.datasource.config.redis.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:33
 */
@Service("dicService")
public class DicServiceImpl implements DicService, CacheInitService {
	@Resource
	private DicDao dicDao;

	@Resource
	private RedisUtils redisUtils;

	@Resource
	private BaseDicService baseDicService;

	@Override
	public Dic getObjById(Long id) {
		return dicDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Dic> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(dicDao.selectList(map));
	}

	@Override
	public List<Dic> getAll() {
		return dicDao.selectAll();
	}

	@Override
	public void save(Dic dic) {
            dicDao.insert(dic);
	}

	@Override
	public void update(Dic dic) {
            dicDao.updateByPrimaryKeySelective(dic);
	}

	@Override
	public void delete(Long id) {
            dicDao.deleteByPrimaryKey(id);
	}

	@Override
	public boolean checkDicVal(Long baseId, Long id, String val) {
		Map<String, Object> param = new HashMap<>(3);
		param.put("id", id);
		param.put("baseId", baseId);
		param.put("val", val);
		return dicDao.selectCount4Uk(param) == 0;
	}

	@Override
	public void enable(List<Long> ids) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("ids", ids);
		map.put("flag", "1");
		dicDao.updateEnabled(map);
	}

	@Override
	public void disable(List<Long> ids) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("ids", ids);
		map.put("flag", "0");
		dicDao.updateEnabled(map);
	}

	@Override
	public List<Dic> getListByBaseDic(Long baseDicId) {
		return dicDao.selectListByBaseDic(baseDicId);
	}

	@Override
	public Map<String, String> getDicNameMap() {
		Map<String, String> dicMap = redisUtils.hGetAll(CacheConstants.KEY_DIC_NAME, String.class);
		if (dicMap == null) {
			dicMap = new HashMap<>(1);
		}
		return dicMap;
	}

	/**
	 * 获得字典哈希表
	 *
	 * @return [BaseDic.code]: [List<Dic>]
	 */
	@Override
	public Map<String, List> getDicListMap() {
		Map<String, List> dicMap = redisUtils.hGetAll(CacheConstants.KEY_DIC_LIST, List.class);
		if (dicMap == null) {
			dicMap = new HashMap<>(1);
		}
		return dicMap;
	}

	@Override
	public String getName() {
		return "字典缓存";
	}

	/**
	 * 将信息放入缓存中
	 */
	@PostConstruct
	@Override
	public void inputCache() {
		redisUtils.del(CacheConstants.KEY_DIC_NAME);

		Map<String, Object> dicMap = new HashMap<>(50);
		Map<String, Object> dicListMap = new HashMap<>(50);

		List<BaseDic> baseDicList = baseDicService.getAll();
		Map<Long, BaseDic> baseDicMap = new HashMap<>(25);
		for (BaseDic baseDic : baseDicList) {
			baseDicMap.put(baseDic.getId(), baseDic);
		}

		List<Dic> dicList = getAll();
		for (Dic dic : dicList) {
			BaseDic baseDic = baseDicMap.get(dic.getBaseId());
			if (baseDic == null) {
				continue;
			}
			dicMap.put(baseDic.getCode() + "-" + dic.getVal(), dic.getName());

			List<Dic> dics = (List<Dic>) dicListMap.computeIfAbsent(baseDic.getCode(), k -> new ArrayList<>(20));
			dics.add(dic);
		}

		redisUtils.hmSet(CacheConstants.KEY_DIC_NAME, dicMap);
		redisUtils.hmSet(CacheConstants.KEY_DIC_LIST, dicListMap);
	}
}
