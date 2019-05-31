package com.myland.framework.authority.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.myland.framework.common.utils.validator.group.AddGroup;
import com.myland.framework.common.utils.validator.group.UpdateGroup;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 角色
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Data
public class Role {

	/**
	 * 主键ID
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 角色名称
	 */
	@NotBlank(message = "角色名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Size(max = 32, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 角色描述
	 */
	@Size(max = 100, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String description;
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
	 * 创建人
	 */
	private String creatorName;

	/**
	 * 用户列表
	 */
	private String users;

	public Date getCreateTime() {
		return ObjectUtils.clone(createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = ObjectUtils.clone(createTime);
	}
}
