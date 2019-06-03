package com.myland.framework.authority.login;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.myland.framework.authority.config.ConfigService;
import com.myland.framework.authority.consts.CacheConstants;
import com.myland.framework.authority.dataAuth.DataAuthService;
import com.myland.framework.authority.dic.DicService;
import com.myland.framework.authority.menu.MenuService;
import com.myland.framework.authority.po.Config;
import com.myland.framework.authority.domain.LoginUser;
import com.myland.framework.authority.po.Menu;
import com.myland.framework.authority.po.User;
import com.myland.framework.authority.user.UserService;
import com.myland.framework.authority.utils.SystemConfig;
import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.shiro.ShiroUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2018-07-02 8:37
 */
@Controller
@RequestMapping("login")
public class LoginController {

	@Resource
	private Producer producer;

	@Resource
	private UserService userService;

	@Resource
	private DicService dicService;

	@Resource
	private ConfigService configService;

	@Resource
	private DataAuthService dataAuthService;

	@Resource
	private MenuService menuService;

	/**
	 * 生成验证码
	 *
	 * @param response 响应
	 * @throws ServletException Servlet异常
	 * @throws IOException      IO异常
	 */
	@GetMapping("/captcha")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		out.flush();
	}

	/**
	 * 登录
	 */
	@ResponseBody
	@PostMapping("/login")
	public ResponseMsg login(@RequestBody @Validated Map<String, String> paramMap) {
		String username = paramMap.get("username");
		String password = paramMap.get("password");

		String captchaEnabledStr = SystemConfig.getCaptchaEnabled().getValue();
		boolean captchaEnabled = BooleanUtils.toBoolean(captchaEnabledStr);
		if (captchaEnabled) {
			String captcha = paramMap.get("captcha");
			String sessionCaptcha = (String) ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
			if (!captcha.equalsIgnoreCase(sessionCaptcha)) {
				return ResponseMsg.error(501, "验证码不正确");
			}
		}

		try {
			Subject subject = ShiroUtils.getSubject();
			//sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			return ResponseMsg.ok(subject.getSession().getId());
		} catch (AuthenticationException e) {
			return ResponseMsg.error(e.getMessage());
		}
	}

	@ResponseBody
	@GetMapping("/user")
	public ResponseMsg user(LoginUser loginUser) {
		// 用户信息
		User userDb = userService.getObjById(loginUser.getId());
		userDb.setPasswd(null);

		// 用户目录、菜单列表
		List<Menu> menuTree = menuService.getPermissionTree(userDb.getId(), null);

		// 用户权限列表
		Set<String> perms = userService.getUserPermissions(userDb.getId());

		return ResponseMsg.ok().put("userInfo", userDb).put("menuTree", menuTree).put("perms", perms);
	}

	/**
	 * 退出
	 */
	@ResponseBody
	@GetMapping("logout")
	public ResponseMsg logout() {
		ShiroUtils.logout();
		return ResponseMsg.ok();
	}

	/**
	 * 解锁屏幕
	 */
	@ResponseBody
	@PostMapping("unlock")
	public ResponseMsg unlock(@RequestBody Map<String, String> paramMap, LoginUser loginUser) {
		String password = paramMap.get("password");
		User user = userService.getObjById(loginUser.getId());

		// 密码错误
		if (!user.getPasswd().equals(new Sha256Hash(password).toHex())) {
			return ResponseMsg.error("密码不正确");
		}
		return ResponseMsg.ok("欢迎回来");
	}
}
