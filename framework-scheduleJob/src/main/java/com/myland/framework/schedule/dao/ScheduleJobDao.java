package com.myland.framework.schedule.dao;

import com.myland.framework.common.base.BaseDao;
import com.myland.framework.schedule.po.ScheduleJobEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 定时任务
 *
 * @author SunYanQing
 */
@Repository
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
