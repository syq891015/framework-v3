package com.myland.framework.authority.cache;

import com.myland.framework.authority.po.File;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.datasource.config.redis.CacheInitService;
import com.myland.framework.web.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存
 * @author SunYanQing
 * @version 1.0
 * @date 2019/4/11 17:11
 */
@RestController
@RequestMapping("/sys/caches")
@Validated
@Slf4j
public class CacheController {
	/**
	 * 列表
	 */
	@RequiresPermissions("sys:cache:list")
	@GetMapping
	public ResponseMsg list() {
		List<Map<String, String>> cacheList = new ArrayList<>(10);
		Map<String, CacheInitService> beanMap = SpringContextUtils.getBeans(CacheInitService.class);
		for (Map.Entry<String, CacheInitService> cacheInitServiceEntry : beanMap.entrySet()) {
			String serviceName = cacheInitServiceEntry.getKey();
			String name = cacheInitServiceEntry.getValue().getName();
			Map<String, String> cacheMap = new HashMap<>(2);
			cacheMap.put("name", name);
			cacheMap.put("serviceName", serviceName);
			cacheList.add(cacheMap);
		}
		return ResponseMsg.ok(cacheList);
	}

	/**
	 * 刷新缓存
	 */
	@GetMapping("/refresh")
	@RequiresPermissions("sys:cache:refresh")
	public ResponseMsg refresh(@RequestParam("serviceNames") String serviceNames) {
		String[] serviceNameAry = serviceNames.split(",");
		for (String serviceName : serviceNameAry) {
			CacheInitService cacheInitService = SpringContextUtils.getBean(serviceName, CacheInitService.class);
			cacheInitService.inputCache();
		}
		return ResponseMsg.ok();
	}
}
