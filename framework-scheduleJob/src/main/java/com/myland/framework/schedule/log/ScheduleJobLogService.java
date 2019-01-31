package com.myland.framework.schedule.log;


import com.github.pagehelper.PageInfo;
import com.myland.framework.common.base.BaseService;
import com.myland.framework.schedule.po.ScheduleJobLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * @author SunYanQing
 */
public interface ScheduleJobLogService extends BaseService<ScheduleJobLogEntity> {

	@Override
	default void save(ScheduleJobLogEntity scheduleJobLogEntity) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(ScheduleJobLogEntity scheduleJobLogEntity) {

	}

	@Override
	default ScheduleJobLogEntity getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<ScheduleJobLogEntity> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<ScheduleJobLogEntity> getAll() {
		return null;
	}
}
