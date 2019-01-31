package com.myland.framework.authority.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 字典大类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Data
public class BaseDic {

	/**
	 * 字典大类ID
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 字典大类名称（中文名称）
	 */
	@NotBlank(message = "字典大类名称不能为空")
	private String name;
	/**
	 * 字典大类代码
	 */
	@NotBlank(message = "字典大类代码不能为空")
	private String code;
	/**
	 * 字典大类描述
	 */
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
