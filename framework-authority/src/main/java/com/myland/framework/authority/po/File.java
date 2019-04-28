package com.myland.framework.authority.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.myland.framework.web.utils.validator.group.AddGroup;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 文件表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-04-10 15:44:34
 */
@Data
public class File {

	/**
	 * 主键
	 */
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 文件名
	 */
	@Size(max = 50, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String fileName;
	/**
	 * 原始文件名
	 */
	@Size(max = 512, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String origFileName;
	/**
	 * 文件夹
	 */
	@Size(max = 200, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String dir;
	/**
	 * 文件大小，字节数
	 */
	private Long fileSize;
	/**
	 * 文件类型：可填写image, html, text, video, audio, flash
	 */
	@Size(max = 10, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String fileType;
	/**
	 * 文件扩展名：jpg、swf、bmp等
	 */
	@Size(max = 20, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String fileExtension;
	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long creator;
	private String creatorName;
	/**
	 * 修改人
	 */
	private Long modifier;
	private String modifierName;
	/**
	 * 修改时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;
	/**
	 * 备注
	 */
	@Size(max = 500, message = "超出长度限制", groups = {AddGroup.class, UpdateGroup.class})
	private String comment;
	/**
	 * 删除标志，1已删除，0正常
	 */
	private boolean deleted;

	private String url;

	public Date getCreateTime() {
		return ObjectUtils.clone(createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = ObjectUtils.clone(createTime);
	}
}
