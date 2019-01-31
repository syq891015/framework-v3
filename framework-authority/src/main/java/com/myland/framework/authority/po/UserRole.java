package com.myland.framework.authority.po;

import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户角色表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Data
public class UserRole {

	/**
	 * 用户ID
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long userId;
	/**
	 * 角色ID
	 */
	private Long roleId;

}
