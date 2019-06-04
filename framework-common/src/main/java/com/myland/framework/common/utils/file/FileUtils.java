package com.myland.framework.common.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2019/6/3 17:52
 */
@Slf4j
public class FileUtils {

	public static void writeNewFile(String dir, String fileName, String base64) {

		assert StringUtils.isNotBlank(dir);
		assert StringUtils.isNotBlank(fileName);
		assert StringUtils.isNotBlank(base64);

		File dirFile = new File(dir);
		// 判断目录文件夹是否存在
		boolean dirExists = dirFile.exists();
		if (!dirExists) {
			// 目录文件不存在，新建目录
			boolean mkdirsSuccess = dirFile.mkdirs();
			if (!mkdirsSuccess) {
				String warn = String.format("###权限不足[%s]", dir);
				log.warn(warn);
				throw new RuntimeException(warn);
			}
		} else {
			// 目录文件存在，判断是否是目录文件，如果不是目录文件则抛异常
			if (!dirFile.isDirectory()) {
				String warn = String.format("###不是目录[%s]", dir);
				log.warn(warn);
				throw new RuntimeException(warn);
			}
		}

		// 创建文件
		String fileFullName = dir + "/" + fileName;
		File destFile = new File(fileFullName);
		boolean destFileExists = destFile.exists();
		if (!destFileExists) {
			boolean createRs;
			try {
				createRs = destFile.createNewFile();
			} catch (IOException e) {
				String warn = String.format("###权限不足[%s]", fileFullName);
				log.error(warn, e);
				throw new RuntimeException(warn, e);
			}
			if (!createRs) {
				String warn = String.format("###权限不足[%s]", fileFullName);
				log.warn(warn);
				throw new RuntimeException(warn);
			}
		} else {
			String warn = String.format("###文件冲突[%s]", fileFullName);
			log.warn(warn);
			throw new RuntimeException(warn);
		}

		try {
			org.apache.commons.io.FileUtils.writeByteArrayToFile(destFile, Base64.decodeBase64(base64));
		} catch (IOException e) {
			String warn = String.format("###权限不足[%s]", fileFullName);
			log.error(warn, e);
			throw new RuntimeException(warn, e);
		}
	}
}
