package com.myland.framework.authority.dic;

import com.myland.framework.authority.po.Dic;
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
 * 字典
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:33
 */
@RestController
@RequestMapping("/sys/dics")
@Validated
public class DicController extends BaseController {
	@Resource
	private DicService dicService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("sys:dic:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(dicService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("sys:dic:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
        Dic dic =dicService.getObjById(id);
		return ResponseMsg.ok(dic);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("sys:dic:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加字典")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) Dic dic) {
		boolean noConflict = dicService.checkDicVal(dic.getBaseId(), null, dic.getVal());
		if (!noConflict) {
			return ResponseMsg.error("同种字典大类下编码值存在重复!");
		}
        dicService.save(dic);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("sys:dic:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改字典")
	public ResponseMsg update(@PathVariable("id") Long id, @RequestBody @Validated(UpdateGroup.class) Dic dic) {
		boolean noConflict = dicService.checkDicVal(dic.getBaseId(), id, dic.getVal());
		if (!noConflict) {
			return ResponseMsg.error("同种字典大类下编码值存在重复!");
		}

        dic.setId(id);
        dicService.update(dic);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("sys:dic:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除字典")
	public ResponseMsg delete(@PathVariable("id") Long id) {
        dicService.delete(id);
		return ResponseMsg.ok();
	}

	/**
	 * 启用
	 */
	@PostMapping("/enable")
	@RequiresPermissions("sys:dic:enable")
	@SysUserLog(type = LogTypeEnum.update, operation = "启用字典")
	public ResponseMsg enable(@RequestBody @NotEmpty(message = "至少选择一项") List<Long> ids) {
		dicService.enable(ids);
		return ResponseMsg.ok();
	}

	/**
	 * 禁用
	 */
	@PostMapping("/disable")
	@RequiresPermissions("sys:dic:disable")
	@SysUserLog(type = LogTypeEnum.update, operation = "禁用字典")
	public ResponseMsg disable(@RequestBody @NotEmpty(message = "至少选择一项") List<Long> ids) {
		dicService.disable(ids);
		return ResponseMsg.ok();
	}

}
