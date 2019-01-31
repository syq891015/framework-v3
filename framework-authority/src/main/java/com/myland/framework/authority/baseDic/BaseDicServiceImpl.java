package com.myland.framework.authority.baseDic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.dao.BaseDicDao;
import com.myland.framework.authority.po.BaseDic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典大类Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Service("baseDicService")
public class BaseDicServiceImpl implements BaseDicService {
	@Resource
	private BaseDicDao baseDicDao;

	@Override
	public BaseDic getObjById(Long id) {
		return baseDicDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<BaseDic> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(baseDicDao.selectList(map));
	}

	@Override
	public List<BaseDic> getAll() {
		return baseDicDao.selectAll();
	}

	@Override
	public boolean checkCodeUnique(String code, Long id) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("code", code);
		map.put("id", id);
		return baseDicDao.selectCountByCode(map) == 0;
	}

	@Override
	public List<BaseDic> getAll(Map<String, Object> paramMap) {
		return baseDicDao.selectAll(paramMap);
	}

	@Override
	public void save(BaseDic baseDic) {
		baseDicDao.insert(baseDic);
	}

	@Override
	public void update(BaseDic baseDic) {
		baseDicDao.updateByPrimaryKeySelective(baseDic);
	}

	@Override
	public void delete(Long id) {
		baseDicDao.deleteByPrimaryKey(id);
	}

}
