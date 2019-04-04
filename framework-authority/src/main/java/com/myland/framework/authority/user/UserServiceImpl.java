package com.myland.framework.authority.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.consts.UserConstants;
import com.myland.framework.authority.dao.UserDao;
import com.myland.framework.authority.menu.MenuService;
import com.myland.framework.authority.po.Menu;
import com.myland.framework.authority.po.User;
import com.myland.framework.common.consts.CharacterConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Resource
	private MenuService menuService;

	@Override
	public User getObjById(Long id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<User> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(userDao.selectList(map));
	}

	@Override
	public List<User> getAll() {
		return userDao.selectAll();
	}

	@Override
	public Set<String> getUserPermissions(Long userId) {
		List<String> permsList;

		// 超级管理员，拥有最高权限
		if (UserConstants.SUPER_ADMIN.compareTo(userId) == 0) {
			List<Menu> menuList = menuService.getAll();
			permsList = new ArrayList<>(menuList.size());
			for (Menu menu : menuList) {
				permsList.add(menu.getPerms());
			}
		} else {
			permsList = menuService.getPermsByUserId(userId);
		}

		//用户权限列表
		Set<String> permsSet = new HashSet<>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(CharacterConstants.COMMA_EN)));
		}
		return permsSet;
	}

	@Override
	public User getByAccount(String username) {
		return userDao.selectByAccount(username);
	}

	@Override
	public List<Long> getRoleIdListByUserId(Long userId) {
		return userDao.selectRoleIdListByUserId(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void boundRole(Long userId, List<Long> roleIds) {
		delRoleRelationShip(userId);

		Map<String, Object> paramMap = new HashMap<>(2);
		paramMap.put("userId", userId);
		paramMap.put("roleIdList", roleIds);
		insertRoleRelationShip(paramMap);
	}

	@Override
	public void delRoleRelationShip(Long userId) {
		userDao.delRoleRelationShip(userId);
	}

	@Override
	public void insertRoleRelationShip(Map<String, Object> paramMap) {
		userDao.insertRoleRelationShip(paramMap);
	}

	@Override
	public void save(User user) {
            userDao.insert(user);
	}

	@Override
	public void update(User user) {
            userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public void delete(Long id) {
            userDao.deleteByPrimaryKey(id);
	}

}
