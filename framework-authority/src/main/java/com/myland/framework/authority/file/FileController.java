package com.myland.framework.authority.file;

import com.myland.framework.authority.config.ConfigService;
import com.myland.framework.authority.consts.CacheConstants;
import com.myland.framework.authority.po.Config;
import com.myland.framework.authority.po.File;
import com.myland.framework.authority.domain.LoginUser;
import com.myland.framework.common.base.BaseController;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.datasource.config.redis.CacheInitService;
import com.myland.framework.logging.annotation.SysUserLog;
import com.myland.framework.logging.consts.LogTypeEnum;
import com.myland.framework.web.utils.SpringContextUtils;
import com.myland.framework.web.utils.validator.group.AddGroup;
import com.myland.framework.web.utils.validator.group.UpdateGroup;
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

	@Resource
	private ConfigService configService;

	/**
	 * 列表
	 */
	@GetMapping(params = {"pageNum", "pageSize"})
	@RequiresPermissions("sys:file:list")
	public ResponseMsg list(@RequestParam Map<String, Object> params) {
		Config config = configService.getConfigInCache(CacheConstants.HKEY_FILE_ACCESS_URL);
		if (config == null) {
			log.warn("CONFIG[File-Access-Url]未配置");
		}
		return ResponseMsg.ok(fileService.getList4Page(params)).put("fileAccessUrl", config != null ? config.getValue() : "");
	}


	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("sys:file:info")
	public ResponseMsg info(@PathVariable("id") Long id) {
        File file = fileService.getObjById(id);
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
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@RequiresPermissions("sys:file:upload")
	public ResponseMsg upload(@RequestParam("files") MultipartFile[] srcFiles, LoginUser loginUser) {
		try {
			return fileService.uploadFiles(srcFiles, loginUser);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseMsg.error(e.getMessage());
		}
	}

	/**
	 * 重新上传文件
	 *
	 * @param srcFile 源文件
	 */
	@RequestMapping(value = "/{id}/reUpload", method = RequestMethod.POST)
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