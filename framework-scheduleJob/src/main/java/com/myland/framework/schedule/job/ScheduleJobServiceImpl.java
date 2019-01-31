package com.myland.framework.schedule.job;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.schedule.common.ScheduleStatus;
import com.myland.framework.schedule.common.ScheduleUtils;
import com.myland.framework.schedule.dao.ScheduleJobDao;
import com.myland.framework.schedule.po.ScheduleJobEntity;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SunYanQing
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	@Resource
	private Scheduler scheduler;
	@Resource
	private ScheduleJobDao schedulerJobDao;

	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init() {
		List<ScheduleJobEntity> scheduleJobList = schedulerJobDao.selectList(new HashMap<>(0));
		for (ScheduleJobEntity scheduleJob : scheduleJobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
			//如果不存在，则创建
			if (cronTrigger == null) {
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
			} else {
				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(ScheduleJobEntity scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
		schedulerJobDao.insert(scheduleJob);
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long jobId) {
		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
		schedulerJobDao.deleteByPrimaryKey(jobId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(ScheduleJobEntity scheduleJob) {
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

		schedulerJobDao.updateByPrimaryKeySelective(scheduleJob);
	}


	@Override
	public ScheduleJobEntity getObjById(Long jobId) {
		return schedulerJobDao.selectByPrimaryKey(jobId);
	}

	@Override
	public PageInfo<ScheduleJobEntity> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(schedulerJobDao.selectList(map));
	}

	@Override
	public int updateBatch(List<Long> jobIds, int status) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("list", jobIds);
		map.put("status", status);
		return schedulerJobDao.updateBatch(map);
	}

	@Override
	public void run(List<Long> jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.run(scheduler, getObjById(jobId));
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pause(List<Long> jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.pauseJob(scheduler, jobId);
		}

		updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void resume(List<Long> jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.resumeJob(scheduler, jobId);
		}

		updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
	}
}
