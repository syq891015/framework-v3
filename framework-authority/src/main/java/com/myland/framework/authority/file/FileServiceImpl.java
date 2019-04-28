package com.myland.framework.authority.file;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.config.ConfigService;
import com.myland.framework.authority.consts.CacheConstants;
import com.myland.framework.authority.dao.FileDao;
import com.myland.framework.authority.po.Config;
import com.myland.framework.authority.po.File;
import com.myland.framework.authority.domain.LoginUser;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.common.utils.file.FileTypeUtils;
import com.myland.framework.common.utils.time.DateFmtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文件表Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-04-10 15:44:34
 */
@Slf4j
@Service("fileService")
public class FileServiceImpl implements FileService {
	@Resource
	private FileDao fileDao;

	@Resource
	private ConfigService configService;

	@Override
	public File getObjById(Long id) {
		return fileDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<File> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(fileDao.selectList(map));
	}

	@Override
	public List<File> getAll() {
		return fileDao.selectAll();
	}

	@Override
	public void save(File file) {
		fileDao.insert(file);
	}

	@Override
	public void update(File file) {
		fileDao.updateByPrimaryKeySelective(file);
	}

	@Override
	public void delete(Long id) {
		fileDao.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseMsg uploadFiles(MultipartFile[] srcFiles, LoginUser loginUser) {
		Config config = configService.getConfigInCache(CacheConstants.HKEY_FILE_ACCESS_URL);
		if (config == null) {
			log.warn("CONFIG[File-Access-Url]未配置");
			return ResponseMsg.error("CONFIG[File-Access-Url]未配置");
		}
		String accessUrl = config.getValue();

		ResponseMsg responseMsg = getUploadDir();
		if (!responseMsg.isOk()) {
			return responseMsg;
		}
		// 上传文件路径
		String uploadDir = (String) responseMsg.get("uploadDir");
		// 访问文件的根目录
		String accessDir = (String) responseMsg.get("accessDir");
		// 上传文件夹
		String dir = DateFmtUtils.currentIsoYyyyMMdd();

		// 文件夹路径
		String dir2Lv = accessDir + java.io.File.separator + dir;
		String dirPath = uploadDir + java.io.File.separator + dir2Lv;
		responseMsg = createDir(dirPath);
		if (!responseMsg.isOk()) {
			return responseMsg;
		}

		Long loginUserId = loginUser.getId();

		File[] fileAry = new File[srcFiles.length];
		int i = 0;
		for (MultipartFile srcFile : srcFiles) {
			// 保存文件到磁盘
			File file = saveFile(srcFile, uploadDir, dir2Lv);
			// 保存文件到数据库
			file.setCreator(loginUserId);
			save(file);
			file.setUrl(accessUrl + file.getDir() + "/" + file.getFileName());
			fileAry[i++] = file;
		}
		return ResponseMsg.ok(fileAry);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseMsg reUploadFile(Long fileId, MultipartFile srcFile, LoginUser loginUser) {
		Config config = configService.getConfigInCache(CacheConstants.HKEY_FILE_ACCESS_URL);
		if (config == null) {
			log.warn("CONFIG[File-Access-Url]未配置");
			return ResponseMsg.error("CONFIG[File-Access-Url]未配置");
		}
		String accessUrl = config.getValue();

		ResponseMsg responseMsg = getUploadDir();
		if (!responseMsg.isOk()) {
			return responseMsg;
		}
		// 上传文件路径
		String uploadDir = (String) responseMsg.get("uploadDir");

		File fileDB = getObjById(fileId);
		String dir = fileDB.getDir();

		// 文件夹路径
		String dirPath = uploadDir + java.io.File.separator + dir;
		responseMsg = createDir(dirPath);
		if (!responseMsg.isOk()) {
			return responseMsg;
		}

		Long loginUserId = loginUser.getId();
		// 保存文件到磁盘
		File file = saveFile(srcFile, uploadDir, dir);
		// 保存文件到数据库
		file.setModifier(loginUserId);
		// 一个+号代表一次修改
		String comment = file.getComment() == null ? "+" : file.getComment() + "+";
		if (comment.length() > 500) {
			comment = comment.substring(0, 500);
		}
		file.setComment(comment);
		file.setId(fileDB.getId());
		update(file);

		file.setUrl(accessUrl + file.getDir() + "/" + file.getFileName());

		File[] fileAry = new File[1];
		fileAry[0] = file;
		return ResponseMsg.ok(fileAry);
	}

	/**
	 * 创建目录
	 * @param dirPath 目录路径
	 * @return 响应消息
	 */
	private ResponseMsg createDir(String dirPath) {
		java.io.File dirObj = new java.io.File(dirPath);
		if (!dirObj.exists()) {
			boolean mkFlag = dirObj.mkdirs();
			if (!mkFlag) {
				log.warn("创建文件夹权限不足{}", dirPath);
				return ResponseMsg.error(String.format("创建文件夹权限不足[%s]", dirPath));
			}
		}
		return ResponseMsg.ok();
	}

	/**
	 * 获得上传文件的一级目录与访问根目录
	 */
	private ResponseMsg getUploadDir() {
		// 系统配置的上传文件路径
		Config uploadDirConf = configService.getConfigInCache(CacheConstants.HKEY_UPLOAD_DIR);
		if (uploadDirConf == null) {
			log.warn("CONFIG[Upload-Dir]未设置");
			return ResponseMsg.error("CONFIG[Upload-Dir]未设置");
		}

		// 访问文件的根目录
		Config accessDirConf = configService.getConfigInCache(CacheConstants.HKEY_ACCESS_DIR);
		if (accessDirConf == null) {
			log.warn("CONFIG[Access-Dir]未设置");
			return ResponseMsg.error("CONFIG[Access-Dir]未设置");
		}

		// 上传文件路径
		String uploadDir = uploadDirConf.getValue();
		// 访问文件的根目录
		String accessDir = accessDirConf.getValue();
		return ResponseMsg.ok().put("uploadDir", uploadDir).put("accessDir", accessDir);
	}

	/**
	 * 保存文件到磁盘
	 * @param srcFile 源文件
	 * @param uploadDir 上传根目录
	 * @param dir 访问目录+文件上级目录
	 * @throws RuntimeException 爆出异常，全部回滚
	 */
	private File saveFile(MultipartFile srcFile, String uploadDir, String dir) {
		// 文件大小
		long fileSize = srcFile.getSize();

		// 原始文件名
		String origName = srcFile.getOriginalFilename();
		origName = origName == null ? "" : origName;

		// 扩展名
		String extension = origName.substring(origName.lastIndexOf("."));

		// 新文件名
		String fileName = UUID.randomUUID().toString() + extension;

		// 文件夹路径
		String dirPath = uploadDir + java.io.File.separator + dir;
		// 文件全路径
		String filePath = dirPath + java.io.File.separator + fileName;
		java.io.File destFile = new java.io.File(filePath);
		boolean crtFlag;
		try {
			crtFlag = destFile.createNewFile();
		} catch (IOException e) {
			log.error("文件路径无效{}", filePath);
			throw new RuntimeException(String.format("文件路径无效[%s]", filePath));
		}
		if (!crtFlag) {
			log.error("创建文件权限不足{}", filePath);
			throw new RuntimeException(String.format("创建文件权限不足[%s]", filePath));
		}

		// 保存文件
		try {
			FileUtils.copyInputStreamToFile(srcFile.getInputStream(), destFile);
		} catch (IOException e) {
			log.error("目标文件复制失败：{}", e.getMessage());
			throw new RuntimeException("目标文件复制失败：" + e.getMessage());
		}

		// 组装文件
		File file = new File();
		file.setFileExtension(extension);
		file.setFileName(fileName);
		file.setOrigFileName(origName);
		file.setFileSize(fileSize);
		file.setDir(dir);
		file.setFileType(FileTypeUtils.getFileType(extension));
		return file;
	}
}
