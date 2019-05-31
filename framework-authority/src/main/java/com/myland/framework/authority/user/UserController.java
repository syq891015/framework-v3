package com.myland.framework.authority.user;

import com.myland.framework.authority.consts.UserConstants;
import com.myland.framework.authority.domain.LoginUser;
import com.myland.framework.authority.po.User;
import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.logging.annotation.SysUserLog;
import com.myland.framework.logging.consts.LogTypeEnum;
import com.myland.framework.common.utils.validator.group.AddGroup;
import com.myland.framework.common.utils.validator.group.UpdateGroup;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@RestController
@RequestMapping("/auth/users")
@Validated
public class UserController extends BaseController {
	@Resource
	private UserService userService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("auth:user:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(userService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("auth:user:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
        User user =userService.getObjById(id);
		return ResponseMsg.ok(user);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("auth:user:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加用户")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) User user, LoginUser loginUser) {
		user.setCreator(loginUser.getId());
		user.setPasswd(UserConstants.DEFAULT_PASSWD_ENCRYPT);
        userService.save(user);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("auth:user:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改用户")
	public ResponseMsg update(@PathVariable("id") Long id, @RequestBody @Validated(UpdateGroup.class) User user, LoginUser loginUser) {
		user.setId(id);
		user.setModifier(loginUser.getId());
        userService.update(user);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("auth:user:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除用户")
	public ResponseMsg delete(@PathVariable("id") Long id) {
        userService.delete(id);
		return ResponseMsg.ok();
	}

	/**
	 * 获得某个用户下的角色ID集合
	 *
	 * @return 角色ID的集合
	 */
	@GetMapping("/{id}/roles/ids")
	@RequiresPermissions("auth:user:roles")
	public ResponseMsg ids(@PathVariable("id") Long userId) {
		List<Long> roleIdList = userService.getRoleIdListByUserId(userId);
		return ResponseMsg.ok(roleIdList);
	}

	/**
	 * 给某个用户绑定角色
	 */
	@PostMapping("/{id}/roles")
	@RequiresPermissions("auth:user:boundRole")
	@SysUserLog(type = LogTypeEnum.add, operation = "用户绑定角色")
	public ResponseMsg boundRole(@PathVariable("id") Long userId, @RequestBody Map<String, List<Long>> roleIdListMap) {
		List<Long> roleIds = roleIdListMap.get("roleIds");
		if (CollectionUtils.isEmpty(roleIds)) {
			return ResponseMsg.error("绑定角色不能为空");
		}
		userService.boundRole(userId, roleIds);
		return ResponseMsg.ok();
	}

	/**
	 * 给某个用户重置密码
	 */
	@PostMapping("/{id}/resetPwd")
	@RequiresPermissions("auth:user:resetPwd")
	@SysUserLog(type = LogTypeEnum.update, operation = "重置密码")
	public ResponseMsg resetPwd(@PathVariable("id") Long userId) {
		User user = new User();
		user.setId(userId);
		user.setPasswd(UserConstants.DEFAULT_PASSWD_ENCRYPT);
		userService.update(user);
		return ResponseMsg.ok();
	}

	/**
	 * 修改个人密码
	 */
	@PostMapping("/updatePwd")
	public ResponseMsg updatePwd(@RequestBody Map<String, String> paramMap, LoginUser loginUser) {
		User loginUserDB = userService.getObjById(loginUser.getId());
		String oldPasswd = paramMap.get("oldPasswd");
		if (!loginUserDB.getPasswd().equals(new Sha256Hash(oldPasswd).toHex())) {
			return ResponseMsg.error("原密码输入不正确！");
		}
		String password = paramMap.get("passwd");
		User user = new User();
		user.setId(loginUser.getId());
		user.setPasswd(new Sha256Hash(password).toHex());
		userService.update(user);
		return ResponseMsg.ok("修改成功！");
	}
}
