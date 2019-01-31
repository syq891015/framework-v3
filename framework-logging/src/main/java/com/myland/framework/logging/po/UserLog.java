package com.myland.framework.logging.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;

/**
 * 用户操作日志
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-06-30 22:22:53
 */
@Data
public class UserLog {

	/**
	 *
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 操作类型: add, update, del
	 */
	private String type;
	/**
	 * 用户操作
	 */
	private String operation;
	/**
	 * 请求方法
	 */
	private String method;
	/**
	 * 请求参数
	 */
	private String params;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public Date getCreateTime() {
		return ObjectUtils.clone(createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = ObjectUtils.clone(createTime);
	}
}
