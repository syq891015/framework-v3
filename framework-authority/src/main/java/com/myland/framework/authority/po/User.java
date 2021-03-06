package com.myland.framework.authority.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.myland.framework.common.utils.validator.group.AddGroup;
import com.myland.framework.common.utils.validator.group.UpdateGroup;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:34
 */
@Data
public class User implements Serializable {

	/**
	 * 主键
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 账号
	 */
	@NotBlank(message = "账号不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Size(max = 20, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String account;
	/**
	 * 密码
	 */
	@Size(max = 64, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String passwd;
	/**
	 * 姓名
	 */
	@NotBlank(message = "姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Size(max = 64, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 性别 1男 2女
	 */
	private Byte sex;
	/**
	 * 联系电话
	 */
	@Size(max = 32, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String phone;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 状态:0 锁定 1 激活
	 */
	private Byte status;
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
	private boolean deleted;

	/**
	 * 创建人
	 */
	private String creatorName;

	/**
	 * 修改人
	 */
	private String modifierName;

	/**
	 * 角色列表
	 */
	private String roles;

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
