package com.myland.framework.common.utils.file;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 文件扩展名工具类
 *
 * @author SunYanQing
 */
public class FileExtensionUtils {

	private final static Map<String, String> FILE_TYPE_MAP = new HashMap<>();

	static {
		//初始化文件类型信息
		getAllFileType();
	}

	private FileExtensionUtils() {
	}

	/**
	 * 常见文件头信息
	 */
	private static void getAllFileType() {
		//JPEG (jpg)
		FILE_TYPE_MAP.put("jpg", "FFD8FF");
		//PNG (png)
		FILE_TYPE_MAP.put("png", "89504E47");
		//GIF (gif)
		FILE_TYPE_MAP.put("gif", "47494638");
		//TIFF (tif)
		FILE_TYPE_MAP.put("tif", "49492A00");
		//Windows Bitmap (bmp)
		FILE_TYPE_MAP.put("bmp", "424D");
		//CAD (dwg)
		FILE_TYPE_MAP.put("dwg", "41433130");
		//HTML (html)
		FILE_TYPE_MAP.put("html", "68746D6C3E");
		//Rich Text Format (rtf)
		FILE_TYPE_MAP.put("rtf", "7B5C727466");
		FILE_TYPE_MAP.put("xml", "3C3F786D6C");
		FILE_TYPE_MAP.put("zip", "504B0304");
		FILE_TYPE_MAP.put("rar", "52617221");
		//Photoshop (psd)
		FILE_TYPE_MAP.put("psd", "38425053");
		//Email [thorough only] (eml)
		FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");
		//Outlook Express (dbx)
		FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");
		//Outlook (pst)
		FILE_TYPE_MAP.put("pst", "2142444E");
		//MS Word
		FILE_TYPE_MAP.put("xls", "D0CF11E0");
		//MS Excel 注意：word 和 excel的文件头一样
		FILE_TYPE_MAP.put("doc", "D0CF11E0");
		//MS Access (mdb)
		FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");
		//WordPerfect (wpd)
		FILE_TYPE_MAP.put("wpd", "FF575043");
		FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
		//Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put("pdf", "255044462D312E");
		//Quicken (qdf)
		FILE_TYPE_MAP.put("qdf", "AC9EBD8F");
		//Windows Password (pwl)
		FILE_TYPE_MAP.put("pwl", "E3828596");
		//Wave (wav)
		FILE_TYPE_MAP.put("wav", "57415645");
		FILE_TYPE_MAP.put("avi", "41564920");
		//Real Audio (ram)
		FILE_TYPE_MAP.put("ram", "2E7261FD");
		//Real Media (rm)
		FILE_TYPE_MAP.put("rm", "2E524D46");
		FILE_TYPE_MAP.put("mpg", "000001BA");
		//Quicktime (mov)
		FILE_TYPE_MAP.put("mov", "6D6F6F76");
		//Windows Media (asf)
		FILE_TYPE_MAP.put("asf", "3026B2758E66CF11");
		//MIDI (mid)
		FILE_TYPE_MAP.put("mid", "4D546864");
	}

	/**
	 * getImageFileType,获取图片文件实际类型,若不是图片则返回null
	 *
	 * @param file 文件对象
	 * @return fileType
	 */
	public static String getImageFileType(File file) {
		if (isImage(file)) {
			try {
				ImageInputStream iis = ImageIO.createImageInputStream(file);
				Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
				if (!iter.hasNext()) {
					return null;
				}
				ImageReader reader = iter.next();
				iis.close();
				return reader.getFormatName();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/**
	 * 获取文件类型,包括图片,若格式不是已配置的,则返回null
	 *
	 * @param file 文件对象
	 * @return fileType
	 */
	public static String getFileByFile(File file) {
		String filetype = null;
		byte[] b = new byte[50];
		try (InputStream is = new FileInputStream(file)) {
			int total = is.read(b);
			if (total != -1) {
				filetype = getFileTypeByStream(b);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return filetype;
	}

	/**
	 * 根据文件字节数组获得文件类型
	 *
	 * @param b 文件字节数组
	 * @return fileType 文件类型
	 */
	public static String getFileTypeByStream(byte[] b) {
		String fileTypeHex = String.valueOf(getFileTypeHexString(b));
		for (Entry<String, String> entry : FILE_TYPE_MAP.entrySet()) {
			String fileTypeHexValue = entry.getValue();
			if (fileTypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * 判断文件是否为图片
	 *
	 * @return true是，false否
	 */
	public static boolean isImage(File file) {
		boolean flag;
		try {
			BufferedImage bufreader = ImageIO.read(file);
			int width = bufreader.getWidth();
			int height = bufreader.getHeight();
			flag = width != 0 && height != 0;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 获得文件类型十六进制表示
	 *
	 * @param b 文件字节数组
	 * @return fileTypeHex
	 */
	private static String getFileTypeHexString(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder();
		if (b == null || b.length <= 0) {
			return null;
		}
		for (byte aB : b) {
			int v = aB & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}  