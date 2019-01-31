package com.myland.framework.authority.userRole;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.dao.UserRoleDao;
import com.myland.framework.authority.po.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户角色表Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Resource
	private UserRoleDao userRoleDao;

	@Override
	public UserRole getObjById(Long id) {
		return userRoleDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<UserRole> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(userRoleDao.selectList(map));
	}

	@Override
	public List<UserRole> getAll() {
		return userRoleDao.selectAll();
	}

	@Override
	public void save(UserRole userRole) {
            userRoleDao.insert(userRole);
	}

	@Override
	public void update(UserRole userRole) {
            userRoleDao.updateByPrimaryKeySelective(userRole);
	}

	@Override
	public void delete(Long id) {
            userRoleDao.deleteByPrimaryKey(id);
	}

}
