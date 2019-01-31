package com.myland.framework.authority.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 菜单
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Data
public class Menu {

	/**
	 * 主键ID
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 父ID，一级菜单为0
	 */
	private Long pMenuId;
	/**
	 * 菜单名称
	 */
	@NotBlank(message = "菜单名称不能为空")
	private String name;
	/**
	 * 菜单URL
	 */
	private String url;
	/**
	 * 授权
	 */
	private String perms;
	/**
	 * 类型：0=目录 1=菜单 2=功能
	 */
	@NotBlank(message = "类型不能为空")
	private String type;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 排序号
	 */
	private Integer orderNum;
	/**
	 * 创建人
	 */
	private Long creator;
	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 父菜单名称
	 */
	private String pMenuName;

	/**
	 * 创建人
	 */
	private String creatorName;

	public Date getCreateTime() {
		return ObjectUtils.clone(createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = ObjectUtils.clone(createTime);
	}
}
