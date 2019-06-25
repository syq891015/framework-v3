package com.myland.framework.schedule.log;

import com.github.pagehelper.PageInfo;
import com.myland.framework.schedule.po.ScheduleJobLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * @author SunYanQing
 */
public interface ScheduleJobLogService {

	void save(ScheduleJobLogEntity scheduleJobLogEntity);

	void delete(Long id);

	void update(ScheduleJobLogEntity scheduleJobLogEntity);

	ScheduleJobLogEntity getObjById(Long id);

	PageInfo<ScheduleJobLogEntity> getList4Page(Map<String, Object> map);

	List<ScheduleJobLogEntity> getAll();
}
