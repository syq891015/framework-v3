package com.myland.framework.authority.po;

import lombok.Data;

import java.util.List;

/**
 * 行政区划表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-06-20 09:45:20
 */
@Data
public class RegionTree {

	/**
	 * 主键，地区编码。参照《GB-T2260-2007 中华人民共和国行政区划代码》
	 */
	private Integer id;

	/**
	 * 上级行政区划ID
	 */
	private Integer pid;

	/**
	 * 各个上级行政区划ID串，以英文逗号连接
	 */
	private String pids;

	/**
	 * 行政区划名称
	 */
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
	private String postCode;

	/**
	 * 子区划
	 */
	private List<RegionTree> children;
}
