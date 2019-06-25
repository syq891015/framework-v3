package com.myland.framework.authority.dao;

import com.myland.framework.authority.po.File;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 文件表
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2019-04-10 15:44:34
 */
@Repository("fileDao")
public interface FileDao {

	void insert(File file);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKeySelective(File file);

	File selectByPrimaryKey(Long id);

	List<File> selectList(Map<String, Object> map);

	List<File> selectAll();
}
