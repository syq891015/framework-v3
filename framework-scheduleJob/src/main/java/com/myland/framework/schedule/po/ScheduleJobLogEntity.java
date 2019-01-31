package com.myland.framework.schedule.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;

/**
 * 定时执行日志
 *
 * @author abc
 */
@Data
@NoArgsConstructor
public class ScheduleJobLogEntity {

	/**
	 * 日志id
	 */
	private Long logId;

	/**
	 * 任务id
	 */
	private Long jobId;

	/**
	 * spring bean名称
	 */
	private String beanName;

	/**
	 * 方法名
	 */
	private String methodName;

	/**
	 * 参数
	 */
	private String params;

	/**
	 * 任务状态    0：成功    1：失败
	 */
	private Integer status;

	/**
	 * 失败信息
	 */
	private String error;

	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer times;

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
