package com.myland.framework.schedule.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 定时任务
 *
 * @author abc
 */
@Data
@NoArgsConstructor
public class ScheduleJobEntity {

	/**
	 * 任务调度参数key
	 */
	public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

	/**
	 * 任务id
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long jobId;

	/**
	 * spring bean名称
	 */
	@NotBlank(message = "bean名称不能为空")
	private String beanName;

	/**
	 * 方法名
	 */
	@NotBlank(message = "方法名称不能为空")
	private String methodName;

	/**
	 * 参数
	 */
	private String params;

	/**
	 * cron表达式
	 */
	@NotBlank(message = "cron表达式不能为空")
	private String cronExpression;

	/**
	 * 任务状态
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 获取：创建时间
	 *
	 * @return Date
	 */
	public Date getCreateTime() {
		return ObjectUtils.clone(createTime);
	}

	/**
	 * 设置：创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = ObjectUtils.clone(createTime);
	}
}
