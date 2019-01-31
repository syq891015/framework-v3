package com.myland.framework.logging.userLog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.logging.dao.UserLogDao;
import com.myland.framework.logging.po.UserLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户操作日志Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-06-30 22:22:53
 */
@Service("userLogService")
public class UserLogServiceImpl implements UserLogService {
	@Resource
	private UserLogDao userLogDao;

	@Override
	public UserLog getObjById(Long id) {
		return userLogDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<UserLog> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(userLogDao.selectList(map));
	}

	@Override
	public List<UserLog> getAll() {
		return userLogDao.selectAll();
	}

	@Override
	public void save(UserLog userLog) {
		userLogDao.insert(userLog);
	}

	@Override
	public void update(UserLog userLog) {
		userLogDao.updateByPrimaryKeySelective(userLog);
	}

	@Override
	public void delete(Long id) {
		userLogDao.deleteByPrimaryKey(id);
	}

}
