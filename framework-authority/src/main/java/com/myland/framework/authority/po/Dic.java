package com.myland.framework.authority.po;

import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	 * 字典大类ID
	 */
	@NotBlank(message = "字典大类ID不能为空")
	private Long baseId;
	/**
	 * 中文名称
	 */
	@NotBlank(message = "中文名称不能为空")
	private String name;
	/**
	 * 编码
	 */
	@NotBlank(message = "编码不能为空")
	private String val;
	/**
	 * 使用标志，1使用，0禁用
	 */
	private String used;

	/**
	 * 字典大类名称
	 */
	private String baseName;

	/**
	 * 字典大类编码
	 */
	private String baseCode;

}
