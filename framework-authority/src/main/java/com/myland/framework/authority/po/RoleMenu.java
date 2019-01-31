package com.myland.framework.authority.po;

import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 角色菜单表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Data
public class RoleMenu {

	/**
	 * 角色ID
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long roleId;
	/**
	 * 菜单ID
	 */
	@NotBlank(message = "菜单ID不能为空")
	private Long menuId;

}
