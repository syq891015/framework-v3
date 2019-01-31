package com.myland.framework.authority.roleMenu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.dao.RoleMenuDao;
import com.myland.framework.authority.po.RoleMenu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 角色菜单表Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {
	@Resource
	private RoleMenuDao roleMenuDao;

	@Override
	public RoleMenu getObjById(Long id) {
		return roleMenuDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<RoleMenu> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(roleMenuDao.selectList(map));
	}

	@Override
	public List<RoleMenu> getAll() {
		return roleMenuDao.selectAll();
	}

	@Override
	public void save(RoleMenu roleMenu) {
            roleMenuDao.insert(roleMenu);
	}

	@Override
	public void update(RoleMenu roleMenu) {
            roleMenuDao.updateByPrimaryKeySelective(roleMenu);
	}

	@Override
	public void delete(Long id) {
            roleMenuDao.deleteByPrimaryKey(id);
	}

}
