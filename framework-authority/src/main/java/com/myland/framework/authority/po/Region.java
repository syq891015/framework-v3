package com.myland.framework.authority.po;

import com.myland.framework.common.utils.validator.group.AddGroup;
import com.myland.framework.common.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 行政区划表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-06-20 09:45:20
 */
@Data
public class Region {

	/**
	 * 主键，地区编码。参照《GB-T2260-2007 中华人民共和国行政区划代码》
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Integer id;

	/**
	 * 上级行政区划ID
	 */
	private Integer pid;

	/**
	 * 各个上级行政区划ID串，以英文逗号连接
	 */
	@Size(max = 512, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String pids;

	/**
	 * 行政区划名称
	 */
	@Size(max = 256, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String name;

	/**
	 * 级别
	 */
	private Integer lev;

	/**
	 * 顺序号
	 */
	private Integer sn;

	/**
	 * 邮政编码
	 */
	@Size(max = 32, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String postCode;


}
