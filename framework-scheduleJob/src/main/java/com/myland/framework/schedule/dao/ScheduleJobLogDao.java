package com.myland.framework.schedule.dao;

import com.myland.framework.schedule.po.ScheduleJobLogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * @author SunYanQing
 */
@Repository
public interface ScheduleJobLogDao {

	void insert(ScheduleJobLogEntity scheduleJobLogEntity);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(ScheduleJobLogEntity scheduleJobLogEntity);

	ScheduleJobLogEntity selectByPrimaryKey(Long id);

	List<ScheduleJobLogEntity> selectList(Map<String, Object> map);

	List<ScheduleJobLogEntity> selectAll();
}
