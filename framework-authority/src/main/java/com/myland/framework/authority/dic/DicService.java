package com.myland.framework.authority.dic;

import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.po.Dic;
import com.myland.framework.common.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 字典
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:33
 */
public interface DicService extends BaseService<Dic> {

	@Override
	default void save(Dic dic) {

	}

	@Override
	default void delete(Long id) {

	}

	@Override
	default void update(Dic dic) {

	}

	@Override
	default Dic getObjById(Long id) {
		return null;
	}

	@Override
	default PageInfo<Dic> getList4Page(Map<String, Object> map) {
		return null;
	}

	@Override
	default List<Dic> getAll() {
		return null;
	}

	/**
	 * 检查同种字典大类下字典编码的唯一性
	 * @param baseId 字典大类ID
	 * @param id 字典大类ID
	 * @param val 字典编码值
	 * @return true字典编码有效，false字典编码无效
	 */
	boolean checkDicVal(Long baseId, Long id, String val);

	/**
	 * 启用
	 * @param ids 系统配置项ID
	 */
	void enable(List<Long> ids);

	/**
	 * 禁用
	 * @param ids 系统配置项ID
	 */
	void disable(List<Long> ids);

	/**
	 * 查询字典大类下的字典集合
	 * @param baseDicId 字典大类ID
	 * @return 字典集合
	 */
	List<Dic> getListByBaseDic(Long baseDicId);

	/**
	 * 获得字典名称哈希表
	 * @return [BaseDic.code]-[Dic.value]: [Dic.name]
	 */
	Map<String,String> getDicNameMap();

	/**
	 * 获得字典哈希表
	 * @return [BaseDic.code]: [List<Dic>]
	 */
	Map<String,List> getDicListMap();
}
