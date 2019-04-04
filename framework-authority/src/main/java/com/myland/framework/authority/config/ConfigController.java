package com.myland.framework.authority.config;

import com.myland.framework.authority.po.Config;
import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.logging.annotation.SysUserLog;
import com.myland.framework.logging.consts.LogTypeEnum;
import com.myland.framework.web.utils.validator.group.AddGroup;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * 系统配置项
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@RestController
@RequestMapping("/sys/configs")
@Validated
public class ConfigController extends BaseController {
	@Resource
	private ConfigService configService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("sys:config:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(configService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("sys:config:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
		Config config = configService.getObjById(id);
		return ResponseMsg.ok(config);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("sys:config:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加系统配置项")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) Config config) {
		boolean can = configService.checkKeyUnique(config.getKey());
		if (!can) {
			return ResponseMsg.error("配置重复！");
		}
		configService.save(config);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("sys:config:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改系统配置项")
	public ResponseMsg update(@PathVariable("id") Long id, @RequestBody @Validated(UpdateGroup.class) Config config) {
		config.setId(id);
		config.setKey(null);
		configService.update(config);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("sys:config:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除系统配置项")
	public ResponseMsg delete(@PathVariable("id") Long id) {
		configService.delete(id);
		return ResponseMsg.ok();
	}

	/**
	 * 启用
	 */
	@PostMapping("/enable")
	@RequiresPermissions("sys:config:enable")
	@SysUserLog(type = LogTypeEnum.update, operation = "启用系统配置项")
	public ResponseMsg enable(@RequestBody @NotEmpty(message = "至少选择一项") List<Long> ids) {
		configService.enable(ids);
		return ResponseMsg.ok();
	}

	/**
	 * 禁用
	 */
	@PostMapping("/disable")
	@RequiresPermissions("sys:config:disable")
	@SysUserLog(type = LogTypeEnum.update, operation = "禁用系统配置项")
	public ResponseMsg disable(@RequestBody @NotEmpty(message = "至少选择一项") List<Long> ids) {
		configService.disable(ids);
		return ResponseMsg.ok();
	}
}
