package com.myland.framework.common.utils;

import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 分页数据拷贝
 *
 * @author SunYanQing
 * @date 2019/6/5 8:53
 */
public class PageInfoCopyUtil {

	public static <K, J> void copy(PageInfo<K> srcObject, PageInfo<J> destObject, Class<J> clz) {
		CachedBeanCopier.copy(srcObject, destObject);

		List<K> srcList = srcObject.getList();

		if (CollectionUtils.isNotEmpty(srcList)) {
			destObject.setList(CachedBeanCopier.copy(srcList, clz));
		}
	}
}
