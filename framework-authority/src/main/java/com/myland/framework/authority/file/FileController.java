package com.myland.framework.authority.file;

import com.myland.framework.authority.domain.LoginUser;
import com.myland.framework.authority.po.File;
import com.myland.framework.authority.utils.SystemConfig;
import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.common.utils.validator.group.AddGroup;
import com.myland.framework.common.utils.validator.group.UpdateGroup;
import com.myland.framework.logging.annotation.SysUserLog;
import com.myland.framework.logging.consts.LogTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 文件
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-04-10 15:44:34
 */
@RestController
@RequestMapping("/sys/files")
@Validated
@Slf4j
public class FileController extends BaseController {
	@Resource
	private FileService fileService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("sys:file:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		return ResponseMsg.ok(fileService.getList4Page(params)).put("fileAccessUrl", SystemConfig.getFileAccessUrl().getValue());
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("sys:file:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
        File file = fileService.getObjById(id);
		if (file == null) {
			return ResponseMsg.error("文件未找到");
		}
		file.setUrl(SystemConfig.getFileAccessUrl().getValue() + "/" + file.getDir() + "/" + file.getFileName());
		return ResponseMsg.ok(file);
	}

	/**
	 * 添加
	 */
	@PostMapping
	@RequiresPermissions("sys:file:add")
	@SysUserLog(type = LogTypeEnum.add, operation = "添加文件")
	public ResponseMsg save(@RequestBody @Validated(AddGroup.class) File file, LoginUser loginUser) {
		file.setCreator(loginUser.getId());
        fileService.save(file);
		return ResponseMsg.ok();
	}

	/**
	 * 修改
	 */
	@PutMapping("/{id}")
	@RequiresPermissions("sys:file:update")
	@SysUserLog(type = LogTypeEnum.update, operation = "修改文件")
	public ResponseMsg update(@PathVariable("id") Long id, @RequestBody @Validated(UpdateGroup.class) File file) {
        file.setId(id);
        fileService.update(file);
		return ResponseMsg.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("sys:file:delete")
	@SysUserLog(type = LogTypeEnum.del, operation = "删除文件")
	public ResponseMsg delete(@PathVariable("id") Long id) {
        fileService.delete(id);
		return ResponseMsg.ok();
	}

	/**
	 * 上传文件
	 *
	 * @param srcFiles 源文件
	 * @param fileType 文件类型
	 * @param loginUser 当前登录用户
	 */
	@PostMapping(value = "/upload")
	@RequiresPermissions("sys:file:upload")
	public ResponseMsg upload(@RequestParam("files") MultipartFile[] srcFiles, String fileType, LoginUser loginUser) {
		try {
			return fileService.uploadFiles(srcFiles, fileType, loginUser);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseMsg.error(e.getMessage());
		}
	}

	/**
	 * 上传文件
	 *
	 * @param data 源文件Base64字符串
	 * @param fileType 文件类型
	 * @param loginUser 当前登录用户
	 */
	@PostMapping(value = "/base64Up")
	@RequiresPermissions("sys:file:upload")
	public ResponseMsg base64Up(String data, String fileType, LoginUser loginUser) {
		try {
			return fileService.uploadFile(data, fileType, loginUser);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseMsg.error(e.getMessage());
		}
	}

	/**
	 * 重新上传文件
	 *
	 * @param srcFile 源文件
	 * @param loginUser 当前登录用户
	 */
	@PostMapping(value = "/{id}/reUpload")
	@RequiresPermissions("sys:file:reUpload")
	public ResponseMsg reUpload(@PathVariable("id") Long id, @RequestParam("files") MultipartFile srcFile, LoginUser loginUser) {
		try {
			return fileService.reUploadFile(id, srcFile, loginUser);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseMsg.error(e.getMessage());
		}
	}
}
