package com.myland.framework.authority.po;

import com.myland.framework.web.utils.validator.group.AddGroup;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 系统配置项
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Data
public class Config {

	/**
	 * 主键
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 配置键
	 */
	@NotBlank(message = "配置键不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Size(max = 50, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String key;
	/**
	 * 配置值
	 */
	@NotBlank(message = "配置值不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Size(max = 2000, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String value;
	/**
	 * 启用标志，1启用，0禁用
	 */
	private String enabled;
	/**
	 * 备注
	 */
	@Size(max = 500, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String remark;

}
