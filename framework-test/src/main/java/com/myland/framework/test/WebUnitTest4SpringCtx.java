package com.myland.framework.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * WEB环境下的单元测试父类
 * @author SunYanQing
 * @version 1.0
 * @date 19-5-13 下午5:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class WebUnitTest4SpringCtx {

	@Autowired
	public WebApplicationContext ctx;

	protected MockMvc mvc;

	/**
	 * 初始化 MVC 的环境
	 */
	@Before
	public void before() { mvc = MockMvcBuilders.webAppContextSetup(ctx).build(); }
}
