package com.myland.framework.authority.role;

import com.myland.framework.authority.po.Role;
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
import java.util.Map;

/**
 * 角色
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@RestController
@RequestMapping("/auth/roles")
@Validated
public class RoleController extends BaseController {
	@Resource
	private RoleService roleService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("auth:role:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(roleService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("auth:role:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
        Role role =roleService.getObjById(id);
		return ResponseMsg.ok(role);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("auth:role:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加角色")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) Role role) {
        roleService.save(role);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("auth:role:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改角色")
	public ResponseMsg update(@PathVariable("id") Long id, @RequestBody @Validated(UpdateGroup.class) Role role) {
        role.setId(id);
        roleService.update(role);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("auth:role:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除角色")
	public ResponseMsg delete(@PathVariable("id") Long id) {
        roleService.delete(id);
		return ResponseMsg.ok();
	}

}
