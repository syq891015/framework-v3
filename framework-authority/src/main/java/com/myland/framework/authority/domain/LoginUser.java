package com.myland.framework.authority.domain;

import com.myland.framework.web.utils.validator.group.AddGroup;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 领域模型：登录用户
 * @author SunYanQing
 * @version 1.0
 * @date 2019/4/11 15:40
 */
@Data
public class LoginUser implements Serializable {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别 1男 2女
	 */
	private Byte sex;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 头像
	 */
	private String avatar;
}
