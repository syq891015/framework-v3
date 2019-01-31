package com.myland.framework.authority.user;

import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.logging.annotation.SysUserLog;
import com.myland.framework.logging.consts.LogTypeEnum;
import com.myland.framework.shiro.ShiroUtils;
import com.myland.framework.web.utils.validator.group.AddGroup;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import com.myland.framework.authority.po.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@RestController
@RequestMapping("/sys/users")
@Validated
public class UserController extends BaseController {
	@Resource
	private UserService userService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("user:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(userService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("user:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
        User user =userService.getObjById(id);
		return ResponseMsg.ok(user);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("user:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加用户")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) User user) {
        userService.save(user);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("user:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改用户")
	public ResponseMsg update(@PathVariable("id") Long id, @RequestBody @Validated(UpdateGroup.class) User user) {
        user.setId(id);
        userService.update(user);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("user:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除用户")
	public ResponseMsg delete(@PathVariable("id") Long id) {
        userService.delete(id);
		return ResponseMsg.ok();
	}

}
