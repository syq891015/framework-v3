package com.myland.framework.common.utils.file;

import com.myland.framework.common.consts.CharacterConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件类型工具类，判断是否是image, html, text, video, audio, flash其中之一
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-05-22 16:15
 */
public class FileTypeUtils {

	private static final Map<String, String> FILE_TYPE_MAP = new HashMap<>(30);

	static {
		FILE_TYPE_MAP.put(".jpg", "image");
		FILE_TYPE_MAP.put(".svg", "image");
		FILE_TYPE_MAP.put(".jpeg", "image");
		FILE_TYPE_MAP.put(".png", "image");
		FILE_TYPE_MAP.put(".gif", "image");
		FILE_TYPE_MAP.put(".bmp", "image");
		FILE_TYPE_MAP.put(".avi", "video");
		FILE_TYPE_MAP.put(".rmvb", "video");
		FILE_TYPE_MAP.put(".rm", "video");
		FILE_TYPE_MAP.put(".mpg", "video");
		FILE_TYPE_MAP.put(".mpeg", "video");
		FILE_TYPE_MAP.put(".wmv", "video");
		FILE_TYPE_MAP.put(".mp4", "video");
		FILE_TYPE_MAP.put(".avi", "video");
		FILE_TYPE_MAP.put(".mkv", "video");
		FILE_TYPE_MAP.put(".html", "html");
		FILE_TYPE_MAP.put(".htm", "html");
		FILE_TYPE_MAP.put(".txt", "text");
		FILE_TYPE_MAP.put(".pdf", "pdf");
		FILE_TYPE_MAP.put(".docx", "word");
		FILE_TYPE_MAP.put(".doc", "word");
		FILE_TYPE_MAP.put(".csv", "excel");
		FILE_TYPE_MAP.put(".xls", "excel");
		FILE_TYPE_MAP.put(".xlsx", "excel");
		FILE_TYPE_MAP.put(".chm", "chm");
		FILE_TYPE_MAP.put(".zip", "compress");
		FILE_TYPE_MAP.put(".7z", "compress");
		FILE_TYPE_MAP.put(".rar", "compress");
	}

	/**
	 * 通过文件扩展名[如.jpg]获得文件类型
	 *
	 * @param extension 文件扩展名
	 * @return image, html, text, video, audio, flash其中之一
	 */
	public static String getFileType(String extension) {
		if (StringUtils.isBlank(extension)) {
			return "";
		}

		if (!extension.startsWith(CharacterConstants.DOT_EN)) {
			extension = CharacterConstants.DOT_EN + extension;
		}
		String fileType = FILE_TYPE_MAP.get(extension.toLowerCase());
		return fileType == null ? "" : fileType;
	}
}
