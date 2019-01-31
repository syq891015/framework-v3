package com.myland.framework.authority.menu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.dao.MenuDao;
import com.myland.framework.authority.po.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 菜单Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuDao menuDao;

	@Override
	public Menu getObjById(Long id) {
		return menuDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Menu> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(menuDao.selectList(map));
	}

	@Override
	public List<Menu> getAll() {
		return menuDao.selectAll();
	}

	@Override
	public void save(Menu menu) {
            menuDao.insert(menu);
	}

	@Override
	public void update(Menu menu) {
            menuDao.updateByPrimaryKeySelective(menu);
	}

	@Override
	public void delete(Long id) {
            menuDao.deleteByPrimaryKey(id);
	}

}
