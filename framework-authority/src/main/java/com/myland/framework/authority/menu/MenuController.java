package com.myland.framework.authority.menu;

import com.myland.framework.authority.po.Menu;
import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.logging.annotation.SysUserLog;
import com.myland.framework.logging.consts.LogTypeEnum;
import com.myland.framework.shiro.ShiroUtils;
import com.myland.framework.web.utils.validator.group.AddGroup;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 菜单
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@RestController
@RequestMapping("/sys/menus")
@Validated
public class MenuController extends BaseController {
	@Resource
	private MenuService menuService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("menu:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(menuService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("menu:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
		Menu menu = menuService.getObjById(id);
		return ResponseMsg.ok(menu);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("menu:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加菜单")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) Menu menu) {
		if (menu.getPMenuId() == null) {
			menu.setPMenuId(0L);
		}
		menuService.save(menu);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("menu:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改菜单")
	public ResponseMsg update(@PathVariable("id") Long id, @RequestBody @Validated(UpdateGroup.class) Menu menu) {
		menu.setId(id);
		menuService.update(menu);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("menu:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除菜单")
	public ResponseMsg delete(@PathVariable("id") Long id) {
		menuService.delete(id);
		return ResponseMsg.ok();
	}

}
