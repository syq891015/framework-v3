package com.myland.framework.schedule.dao;

import com.myland.framework.common.base.BaseDao;
import com.myland.framework.schedule.po.ScheduleJobLogEntity;
import org.springframework.stereotype.Repository;

/**
 * 定时任务日志
 */
@Repository
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {

}
