package com.myland.framework.authority.file;

import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.common.utils.JsonUtils;
import com.myland.framework.test.WebUnitTest4SpringCtx;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2019/6/3 20:00
 */
@Transactional
@Rollback
public class FileControllerTest extends WebUnitTest4SpringCtx {

	@Test
	public void base64Up() throws Exception {
		String base64Path = getClass().getClassLoader().getResource("base64.txt").getPath();
		File file = new File(base64Path);
		List<String> stringList = FileUtils.readLines(file, StandardCharsets.UTF_8);
		String base64 = stringList.get(0);

		Map<String, String> map = new HashMap<>(2);
		map.put("data", base64);
		map.put("fileType", "Exam-Photo");
		String json = JsonUtils.toJSON(map);

		String res = mvc.perform(post("/sys/files/base64Up").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn().getResponse().getContentAsString();

		ResponseMsg responseMsg = JsonUtils.parseObject(res, ResponseMsg.class);

		Assert.assertTrue("响应不是正确的", responseMsg.isOk());
	}

}