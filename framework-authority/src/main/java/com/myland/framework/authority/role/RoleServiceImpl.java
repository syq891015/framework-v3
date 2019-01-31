package com.myland.framework.authority.role;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.dao.RoleDao;
import com.myland.framework.authority.po.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 角色Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	@Override
	public Role getObjById(Long id) {
		return roleDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Role> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(roleDao.selectList(map));
	}

	@Override
	public List<Role> getAll() {
		return roleDao.selectAll();
	}

	@Override
	public void save(Role role) {
            roleDao.insert(role);
	}

	@Override
	public void update(Role role) {
            roleDao.updateByPrimaryKeySelective(role);
	}

	@Override
	public void delete(Long id) {
            roleDao.deleteByPrimaryKey(id);
	}

}
