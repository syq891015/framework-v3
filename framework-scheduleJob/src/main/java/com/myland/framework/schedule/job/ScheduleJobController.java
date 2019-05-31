package com.myland.framework.schedule.job;

import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.logging.annotation.SysUserLog;
import com.myland.framework.logging.consts.LogTypeEnum;
import com.myland.framework.schedule.po.ScheduleJobEntity;
import com.myland.framework.common.utils.validator.group.AddGroup;
import com.myland.framework.common.utils.validator.group.UpdateGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronExpression;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author SunYanQing
 */
@RestController
@RequestMapping("/sys/scheduleJobs")
@Validated
public class ScheduleJobController extends BaseController {
	@Resource
	private ScheduleJobService scheduleJobService;

	/**
	 * 定时任务列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("sys:scheduleJob:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(scheduleJobService.getList4Page(params));
	}

	/**
	 * 定时任务信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("sys:scheduleJob:info")
	public ResponseMsg info(@PathVariable("id") Long jobId) {

		ScheduleJobEntity schedule = scheduleJobService.getObjById(jobId);

		return ResponseMsg.ok(schedule);
	}

	/**
	 * 保存定时任务
	 */
	@PostMapping
	@RequiresPermissions("sys:scheduleJob:save")
	@SysUserLog(type = LogTypeEnum.add, operation = "保存定时任务")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) ScheduleJobEntity scheduleJobEntity) {

		if (!CronExpression.isValidExpression(scheduleJobEntity.getCronExpression())) {
			return ResponseMsg.error("无效的Cron表达式");
		}

		scheduleJobService.save(scheduleJobEntity);
		return ResponseMsg.ok();
	}

	/**
	 * 修改定时任务
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("sys:scheduleJob:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改定时任务")
	public ResponseMsg update(@PathVariable("id") Long jobId, @RequestBody @Validated(UpdateGroup.class) ScheduleJobEntity scheduleJobEntity) {

		if (!CronExpression.isValidExpression(scheduleJobEntity.getCronExpression())) {
			return ResponseMsg.error("无效的Cron表达式");
		}

		scheduleJobEntity.setJobId(jobId);
		scheduleJobService.update(scheduleJobEntity);
		return ResponseMsg.ok();
	}

	/**
	 * 删除定时任务
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("sys:scheduleJob:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除定时任务")
	public ResponseMsg delete(@PathVariable("id") Long jobId) {
		scheduleJobService.delete(jobId);
		return ResponseMsg.ok();
	}

	/**
	 * 立即执行任务
	 */
	@PostMapping("/run")
	@RequiresPermissions("sys:scheduleJob:run")
	@SysUserLog(type = LogTypeEnum.other, operation = "立即执行任务")
	public ResponseMsg run(@RequestBody @NotEmpty(message = "至少选择一项任务") List<Long> jobIds) {
		scheduleJobService.run(jobIds);
		return ResponseMsg.ok();
	}

	/**
	 * 暂停定时任务
	 */
	@PostMapping("/pause")
	@RequiresPermissions("sys:scheduleJob:pause")
	@SysUserLog(type = LogTypeEnum.other, operation = "暂停定时任务")
	public ResponseMsg pause(@RequestBody @NotEmpty(message = "至少选择一项任务") List<Long> jobIds) {
		scheduleJobService.pause(jobIds);
		return ResponseMsg.ok();
	}

	/**
	 * 恢复定时任务
	 */
	@PostMapping("/resume")
	@RequiresPermissions("sys:scheduleJob:resume")
	@SysUserLog(type = LogTypeEnum.other, operation = "恢复定时任务")
	public ResponseMsg resume(@RequestBody @NotEmpty(message = "至少选择一项任务") List<Long> jobIds) {
		scheduleJobService.resume(jobIds);
		return ResponseMsg.ok();
	}

}
