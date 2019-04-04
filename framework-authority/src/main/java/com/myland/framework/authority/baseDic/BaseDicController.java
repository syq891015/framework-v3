package com.myland.framework.authority.baseDic;

import com.myland.framework.authority.dic.DicService;
import com.myland.framework.authority.po.BaseDic;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典大类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@RestController
@RequestMapping("/sys/baseDics")
@Validated
public class BaseDicController extends BaseController {
	@Resource
	private BaseDicService baseDicService;

	@Resource
	private DicService dicService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("sys:baseDic:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(baseDicService.getList4Page(params));
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("sys:baseDic:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
		BaseDic baseDic = baseDicService.getObjById(id);
		return ResponseMsg.ok(baseDic);
	}

	/**
	 * 查询全部
	 */
	@GetMapping
	@RequiresPermissions("sys:baseDic:all")
	public ResponseMsg all() {
		Map<String, Object> paramMap = new HashMap<>(1);
		paramMap.put("visibility", 1);
		return ResponseMsg.ok(baseDicService.getAll(paramMap));
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("sys:baseDic:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加字典大类")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) BaseDic baseDic) {
		boolean can = baseDicService.checkCodeUnique(baseDic.getCode(), null);
		if (!can) {
			return ResponseMsg.error("编码重复！");
		}
		baseDicService.save(baseDic);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("sys:baseDic:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改字典大类")
	public ResponseMsg update(@PathVariable("id") Long id, @RequestBody @Validated(UpdateGroup.class) BaseDic baseDic) {
		boolean can = baseDicService.checkCodeUnique(baseDic.getCode(), id);
		if (!can) {
			return ResponseMsg.error("编码重复！");
		}
		baseDic.setId(id);
		baseDicService.update(baseDic);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("sys:baseDic:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除字典大类")
	public ResponseMsg delete(@PathVariable("id") Long id) {
		List<Dic> dicList = dicService.getListByBaseDic(id);
		if (dicList.size() > 0) {
			return ResponseMsg.error("有字典设置，无法删除！");
		}
		baseDicService.delete(id);
		return ResponseMsg.ok();
	}

}
