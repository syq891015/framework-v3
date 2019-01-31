package com.myland.framework.schedule.log;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.schedule.dao.ScheduleJobLogDao;
import com.myland.framework.schedule.po.ScheduleJobLogEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * @author SunYanQing
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
	@Resource
	private ScheduleJobLogDao scheduleJobLogDao;


	@Override
	public void save(ScheduleJobLogEntity scheduleJobLogEntity) {
		scheduleJobLogDao.insert(scheduleJobLogEntity);
	}

	@Override
	public ScheduleJobLogEntity getObjById(Long id) {
		return scheduleJobLogDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<ScheduleJobLogEntity> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(scheduleJobLogDao.selectList(map));
	}
}
