package com.myland.framework.schedule.log;

import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.schedule.po.ScheduleJobLogEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 定时任务日志
 * @author SunYanQing
 */
@RestController
@RequestMapping("/sys/scheduleJobLogs")
public class ScheduleJobLogController {
	@Resource
	private ScheduleJobLogService scheduleJobLogService;

	/**
	 * 定时任务日志列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("sys:scheduleJobLog:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(scheduleJobLogService.getList4Page(params));
	}

	/**
	 * 定时任务日志信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("sys:scheduleJobLog:info")
	public ResponseMsg info(@PathVariable("id") Long logId) {
		ScheduleJobLogEntity log = scheduleJobLogService.getObjById(logId);
		return ResponseMsg.ok(log);
	}
}
