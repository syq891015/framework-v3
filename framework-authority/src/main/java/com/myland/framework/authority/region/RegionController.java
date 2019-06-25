package com.myland.framework.authority.region;

import com.myland.framework.authority.domain.LoginUser;
import com.myland.framework.authority.po.Region;
import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.common.utils.validator.group.AddGroup;
import com.myland.framework.common.utils.validator.group.UpdateGroup;
import com.myland.framework.logging.annotation.SysUserLog;
import com.myland.framework.logging.consts.LogTypeEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 行政区划表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-06-20 09:45:20
 */
@RestController
@RequestMapping("/sys/regions")
@Validated
public class RegionController extends BaseController {
	@Resource
	private RegionService regionService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("sys:region:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(regionService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("sys:region:info")
	public ResponseMsg info(@PathVariable("id") Integer id) {
		Region region = regionService.getObjById(Long.valueOf(id));
		return ResponseMsg.ok(region);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("sys:region:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加行政区划表")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) Region region, LoginUser loginUser) {
		regionService.save(region);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("sys:region:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改行政区划表")
	public ResponseMsg update(@PathVariable("id") Integer id, @RequestBody @Validated(UpdateGroup.class) Region region, LoginUser loginUser) {
		region.setId(id);
		regionService.update(region);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("sys:region:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除行政区划表")
	public ResponseMsg delete(@PathVariable("id") Integer id) {
		regionService.delete(Long.valueOf(id));
		return ResponseMsg.ok();
	}

	@GetMapping("/cascade")
	@RequiresPermissions("sys:region:cascade")
	public ResponseMsg cascade() {
		return ResponseMsg.ok(regionService.getCascadeRegion(100000));
	}

	@GetMapping("/{pid}/children")
	@RequiresPermissions("sys:region:children")
	public ResponseMsg children(@PathVariable("pid") Integer pid) {
		return ResponseMsg.ok(regionService.getChildren(pid));
	}
}
