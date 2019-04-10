package com.myland.framework.authority.po;

import com.myland.framework.web.utils.validator.group.AddGroup;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 字典
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:33
 */
@Data
public class Dic {

	/**
	 * 字典ID
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 字典目录ID
	 */
	@NotNull(message = "字典目录ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Long baseId;
	/**
	 * 中文名称
	 */
	@NotBlank(message = "中文名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Size(max = 20, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 编码
	 */
	@NotBlank(message = "编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Size(max = 100, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String val;
	/**
	 * 使用标志，1使用，0禁用
	 */
	private String used;

	/**
	 * 字典目录名称
	 */
	private String baseName;

	/**
	 * 字典目录编码
	 */
	private String baseCode;

}
