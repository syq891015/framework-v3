package com.myland.framework.authority.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Data
public class User {

	/**
	 * 主键
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 账号
	 */
	@NotBlank(message = "账号不能为空")
	private String account;
	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	private String passwd;
	/**
	 * 姓名
	 */
	@NotBlank(message = "姓名不能为空")
	private String name;
	/**
	 * 性别 1男 2女
	 */
	private String sex;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 状态:0 锁定 1 激活
	 */
	private String status;
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
	 * 修改人
	 */
	private Long modifier;
	/**
	 * 修改时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;
	/**
	 * 删除标志，0未删除 1已删除
	 */
	private Integer deleted;

	public Date getCreateTime() {
		return ObjectUtils.clone(createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = ObjectUtils.clone(createTime);
	}

	public Date getModifyTime() {
		return ObjectUtils.clone(modifyTime);
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = ObjectUtils.clone(modifyTime);
	}
}
