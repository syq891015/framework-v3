package com.myland.framework.authority.menu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.consts.UserConstants;
import com.myland.framework.authority.dao.MenuDao;
import com.myland.framework.authority.po.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
	public List<String> getPermsByUserId(Long userId) {
		return menuDao.selectPermsByUserId(userId);
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

	@Override
	public List<Menu> getAllTree(Long pMenuId) {
		if (pMenuId == null) {
			pMenuId = 0L;
		}
		List<Menu> children = getChildren(pMenuId);

		for (Menu childMenu : children) {
			childMenu.setChildren(getAllTree(childMenu.getId()));
		}
		return children;
	}

	@Override
	public List<Menu> getChildren(Long pMenuId) {
		return menuDao.selectChildren(pMenuId);
	}

	@Override
	public List<Menu> getTrunkChildren(Long pMenuId) {
		return menuDao.selectTrunkChildren(pMenuId);
	}

	@Override
	public List<Menu> getPermissionTree(Long userId, Long pMenuId) {

		if (pMenuId == null) {
			pMenuId = 0L;
		}

		// 获得用户有权访问的子菜单
		List<Menu> children;
		// 超级管理员，拥有最高权限
		if (UserConstants.SUPER_ADMIN.compareTo(userId) != 0) {
			children = getPermissionChildren(userId, pMenuId);
		} else {
			children = getTrunkChildren(pMenuId);
		}

		for (Menu childMenu : children) {
			childMenu.setChildren(getPermissionTree(userId, childMenu.getId()));
		}
		return children;
	}

	@Override
	public List<Menu> getPermissionChildren(Long userId, Long pMenuId) {
		Map<String, Long> paramMap = new HashMap<>(2);
		paramMap.put("userId", userId);
		paramMap.put("pMenuId", pMenuId);
		return menuDao.selectPermissionChildren(paramMap);
	}
}
