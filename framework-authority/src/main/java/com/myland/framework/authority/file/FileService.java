package com.myland.framework.authority.file;

import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.domain.LoginUser;
import com.myland.framework.common.base.BaseService;
import com.myland.framework.authority.po.File;
import com.myland.framework.common.message.ResponseMsg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 文件表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-04-10 15:44:34
 */
public interface FileService extends BaseService<File> {

	@Override
	default void save(File file) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(File file) {

	}

	@Override
	default File getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<File> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<File> getAll() {
		return null;
	}

	/**
	 * 批量上传文件
	 * @param srcFiles 源文件列表
	 * @param loginUser 登录用户
	 * @return 响应消息
	 */
	ResponseMsg uploadFiles(MultipartFile[] srcFiles, LoginUser loginUser);

	/**
	 * 重新上传文件
	 * @param fileId 当前文件ID
	 * @param srcFile 源文件
	 * @param loginUser 登录用户
	 * @return 响应消息
	 */
	ResponseMsg reUploadFile(Long fileId, MultipartFile srcFile, LoginUser loginUser);
}
