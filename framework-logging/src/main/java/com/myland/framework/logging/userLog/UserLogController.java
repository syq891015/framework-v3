package com.myland.framework.logging.userLog;

import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.logging.po.UserLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户操作日志
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-06-30 22:22:53
 */
@RestController
@RequestMapping("/sys/userLogs")
public class UserLogController extends BaseController {
	@Resource
	private UserLogService userLogService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("sys:userLog:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(userLogService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("sys:userLog:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
		UserLog userLog = userLogService.getObjById(id);
		return ResponseMsg.ok(userLog);
	}

	/**
	 * 保存
	 */
	@PostMapping
	@RequiresPermissions("sys:userLog:save")
	public ResponseMsg save(@RequestBody UserLog userLog) {
		userLogService.save(userLog);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("sys:userLog:update")
	public ResponseMsg update(@PathVariable("id") Long id, @RequestBody UserLog userLog) {
		userLog.setId(id);
		userLogService.update(userLog);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("sys:userLog:delete")
	public ResponseMsg delete(@PathVariable("id") Long id) {
		userLogService.delete(id);
		return ResponseMsg.ok();
	}

}
