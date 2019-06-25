package com.myland.framework.authority.file;

import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.domain.LoginUser;
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
public interface FileService {

	void save(File file);

	void delete(Long id);

	void update(File file);

	File getObjById(Long id);

	PageInfo<File> getList4Page(Map<String, Object> map);

	List<File> getAll();

	/**
	 * 批量上传文件
	 * @param srcFiles 源文件列表
	 * @param fileType 文件类型
	 * @param loginUser 登录用户
	 * @return 响应消息
	 */
	ResponseMsg uploadFiles(MultipartFile[] srcFiles, String fileType, LoginUser loginUser);

	/**
	 * 重新上传文件
	 * @param fileId 当前文件ID
	 * @param srcFile 源文件
	 * @param loginUser 登录用户
	 * @return 响应消息
	 */
	ResponseMsg reUploadFile(Long fileId, MultipartFile srcFile, LoginUser loginUser);

	/**
	 * 上传文件
	 * @param data 源文件base64
	 * @param fileType 文件类型
	 * @param loginUser 登录用户
	 * @return 响应消息
	 */
	ResponseMsg uploadFile(String data, String fileType, LoginUser loginUser);
}
