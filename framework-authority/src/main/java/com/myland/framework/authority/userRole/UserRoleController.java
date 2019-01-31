package com.myland.framework.authority.userRole;

import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.logging.annotation.SysUserLog;
import com.myland.framework.logging.consts.LogTypeEnum;
import com.myland.framework.shiro.ShiroUtils;
import com.myland.framework.web.utils.validator.group.AddGroup;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import com.myland.framework.authority.po.UserRole;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户角色表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@RestController
@RequestMapping("userRoles")
@Validated
public class UserRoleController extends BaseController {
	@Resource
	private UserRoleService userRoleService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("userRole:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(userRoleService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{userId}")
	@RequiresPermissions("userRole:info")
	public ResponseMsg info(@PathVariable("userId") Long userId) {
        UserRole userRole =userRoleService.getObjById(userId);
		return ResponseMsg.ok(userRole);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("userRole:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加用户角色表")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) UserRole userRole) {
        userRoleService.save(userRole);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{userId}")
	@RequiresPermissions("userRole:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改用户角色表")
	public ResponseMsg update(@PathVariable("userId") Long userId, @RequestBody @Validated(UpdateGroup.class) UserRole userRole) {
        userRole.setUserId(userId);
        userRoleService.update(userRole);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{userId}")
	@RequiresPermissions("userRole:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除用户角色表")
	public ResponseMsg delete(@PathVariable("userId") Long userId) {
        userRoleService.delete(userId);
		return ResponseMsg.ok();
	}

}
