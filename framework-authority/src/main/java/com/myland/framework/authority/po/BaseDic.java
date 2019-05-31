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
 * 字典目录
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Data
public class BaseDic {

	/**
	 * 字典目录ID
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 字典目录名称（中文名称）
	 */
	@NotBlank(message = "字典目录名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Size(max = 50, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 字典目录代码
	 */
	@NotBlank(message = "字典目录代码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Size(max = 50, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String code;
	/**
	 * 字典目录描述
	 */
	@Size(max = 1000, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String description;
	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 可见性，1可见，0不可见
	 */
	private Integer visibility;

	public Date getCreateTime() {
		return ObjectUtils.clone(createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = ObjectUtils.clone(createTime);
	}
}
