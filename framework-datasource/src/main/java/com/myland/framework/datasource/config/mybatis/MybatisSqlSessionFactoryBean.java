package com.myland.framework.datasource.config.mybatis;

import com.myland.framework.common.consts.CharacterConstants;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * mybatis自动扫描别名路径（新增通配符匹配功能）
 *
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年12月9日 上午9:36:23
 */
public class MybatisSqlSessionFactoryBean extends SqlSessionFactoryBean {
	private static final Logger logger = LoggerFactory.getLogger(MybatisSqlSessionFactoryBean.class);
	private static final String ROOT_PATH = "com";
	private static final String[] PATH_REPLACE_ARRAY = {CharacterConstants.RIGHT_SQUARE_BRACKETS};

	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		if (!isStringAvailable(typeAliasesPackage)) {
			super.setTypeAliasesPackage(typeAliasesPackage);
			return;
		}
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		StringBuilder typeAliasesPackageStringBuffer = new StringBuilder();
		try {
			for (String location : typeAliasesPackage.split(CharacterConstants.COMMA_EN)) {
				if (!isStringAvailable(location)) {
					continue;
				}
				location = "classpath*:"
						+ location.trim().replace(CharacterConstants.DOT_EN, CharacterConstants.FORWARD_SLASH);
				typeAliasesPackageStringBuffer.append(getResources(resolver,
						location));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		String replaceRes = replaceResult(typeAliasesPackageStringBuffer.toString());
		typeAliasesPackage = replaceRes.replace(CharacterConstants.FORWARD_SLASH, CharacterConstants.DOT_EN).replace(CharacterConstants.BACK_SLASH, CharacterConstants.DOT_EN);

		if (typeAliasesPackage.endsWith(CharacterConstants.COMMA_EN)) {
			typeAliasesPackage = typeAliasesPackage.substring(0, typeAliasesPackage.length() - 1);
		}
		super.setTypeAliasesPackage(typeAliasesPackage);
	}

	/**
	 * 检查字符串是否是有效的
	 *
	 * @param str 字符串
	 * @return true有效，false无效
	 */
	private boolean isStringAvailable(String str) {
		return StringUtils.isNotBlank(str);
	}

	private String getResources(ResourcePatternResolver resolver,
								String location) throws IOException {
		StringBuilder resourcePathStringBuffer = new StringBuilder();
		for (Resource resource : resolver.getResources(location)) {
			String description = resource == null ? "" : resource
					.getDescription();
			if (!isStringAvailable(description)) {
				continue;
			}
			resourcePathStringBuffer.append(subDescription(description))
					.append(CharacterConstants.COMMA_EN);
		}
		return resourcePathStringBuffer.toString();
	}

	private String subDescription(String description) {
		if (description.contains(CharacterConstants.EXCLAMATION_MARK)) {
			description = description.substring(description.lastIndexOf(CharacterConstants.EXCLAMATION_MARK));
		}
		String str = replaceResult(description.substring(description.indexOf(ROOT_PATH)));
		if (str.endsWith(CharacterConstants.FORWARD_SLASH) || str.endsWith(CharacterConstants.BACK_SLASH)) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	private String replaceResult(String resultStr) {
		for (String replaceStr : PATH_REPLACE_ARRAY) {
			resultStr = resultStr.replace(replaceStr, "");
		}
		return resultStr;
	}
}
