package com.myland.framework.authority.roleMenu;

import com.myland.framework.authority.po.RoleMenu;
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
 * 角色菜单表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@RestController
@RequestMapping("/auth/roleMenus")
@Validated
public class RoleMenuController extends BaseController {
	@Resource
	private RoleMenuService roleMenuService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("auth:roleMenu:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(roleMenuService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{roleId}")
	@RequiresPermissions("auth:roleMenu:info")
	public ResponseMsg info(@PathVariable("roleId") Long roleId) {
        RoleMenu roleMenu =roleMenuService.getObjById(roleId);
		return ResponseMsg.ok(roleMenu);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("auth:roleMenu:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加角色菜单表")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) RoleMenu roleMenu) {
        roleMenuService.save(roleMenu);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{roleId}")
	@RequiresPermissions("auth:roleMenu:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改角色菜单表")
	public ResponseMsg update(@PathVariable("roleId") Long roleId, @RequestBody @Validated(UpdateGroup.class) RoleMenu roleMenu) {
        roleMenu.setRoleId(roleId);
        roleMenuService.update(roleMenu);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{roleId}")
	@RequiresPermissions("auth:roleMenu:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除角色菜单表")
	public ResponseMsg delete(@PathVariable("roleId") Long roleId) {
        roleMenuService.delete(roleId);
		return ResponseMsg.ok();
	}

}
