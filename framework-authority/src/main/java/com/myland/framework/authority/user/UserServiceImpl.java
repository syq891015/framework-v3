package com.myland.framework.authority.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.dao.UserDao;
import com.myland.framework.authority.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
