package com.myland.framework.authority.dao;

import com.myland.framework.common.base.BaseDao;
import com.myland.framework.authority.po.File;
import org.springframework.stereotype.Repository;

/**
 * 文件表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-04-10 15:44:34
 */
@Repository("fileDao")
public interface FileDao extends BaseDao<File> {

}
