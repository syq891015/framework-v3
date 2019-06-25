package com.myland.framework.schedule.dao;

import com.myland.framework.schedule.po.ScheduleJobEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author SunYanQing
 */
@Repository
public interface ScheduleJobDao {

	void insert(ScheduleJobEntity scheduleJobEntity);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(ScheduleJobEntity scheduleJobEntity);

	ScheduleJobEntity selectByPrimaryKey(Long id);

	List<ScheduleJobEntity> selectList(Map<String, Object> map);

	List<ScheduleJobEntity> selectAll();
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
