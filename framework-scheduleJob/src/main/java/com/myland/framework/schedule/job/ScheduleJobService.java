package com.myland.framework.schedule.job;

import com.github.pagehelper.PageInfo;
import com.myland.framework.common.base.BaseService;
import com.myland.framework.schedule.po.ScheduleJobEntity;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 * @author SunYanQing
 */
public interface ScheduleJobService extends BaseService<ScheduleJobEntity> {

	@Override
	default void save(ScheduleJobEntity scheduleJobEntity) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(ScheduleJobEntity scheduleJobEntity) {

	}

	@Override
	default ScheduleJobEntity getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<ScheduleJobEntity> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<ScheduleJobEntity> getAll() {
		return null;
	}

	/**
	 * 批量更新定时任务状态
	 * @param jobIds 任务ID集合
	 * @param status 任务执行状态
	 * @return 影响行数
	 */
	int updateBatch(List<Long> jobIds, int status);

	/**
	 * 立即执行
	 * @param jobIds 任务ID集合
	 */
	void run(List<Long> jobIds);

	/**
	 * 暂停运行
	 * @param jobIds 任务ID集合
	 */
	void pause(List<Long> jobIds);

	/**
	 * 恢复运行
	 * @param jobIds 任务ID集合
	 */
	void resume(List<Long> jobIds);
}
