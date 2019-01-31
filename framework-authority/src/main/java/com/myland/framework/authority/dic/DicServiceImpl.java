package com.myland.framework.authority.dic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.dao.DicDao;
import com.myland.framework.authority.po.Dic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class DicServiceImpl implements DicService {
	@Resource
	private DicDao dicDao;

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
}
