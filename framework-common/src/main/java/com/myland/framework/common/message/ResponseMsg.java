package com.myland.framework.common.message;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应报文
 *
 * @author SunYanQing
 */
public class ResponseMsg extends HashMap<String, Object> {

	/**
	 * 返回消息编码
	 */
	private static final String R_KEY_CODE = "code";

	/**
	 * 返回消息文本
	 */
	private static final String R_KEY_MSG = "msg";

	/**
	 * 返回消息数据
	 */
	private static final String R_KEY_DATA = "data";

	/**
	 * 默认成功消息文本
	 */
	private static final String R_MSG_SUCCESS = "SUCCESS";

	public ResponseMsg() {
		put(R_KEY_CODE, 0).put(R_KEY_MSG, R_MSG_SUCCESS);
	}

	public static ResponseMsg ok() {
		return new ResponseMsg();
	}

	public static ResponseMsg ok(String msg) {
		ResponseMsg r = ResponseMsg.ok();
		r.put(R_KEY_MSG, msg);
		return r;
	}

	public static ResponseMsg ok(Object data) {
		ResponseMsg r = ResponseMsg.ok();
		r.put(R_KEY_DATA, data);
		return r;
	}

	public static ResponseMsg ok(String msg, Object data) {
		ResponseMsg r = ResponseMsg.ok(msg);
		r.put(R_KEY_DATA, data);
		return r;
	}

	public static ResponseMsg ok(Map<String, Object> map) {
		ResponseMsg r = new ResponseMsg();
		r.putAll(map);
		return r;
	}

	public static ResponseMsg error(int code, String msg) {
		ResponseMsg r = new ResponseMsg();
		r.put(R_KEY_CODE, code);
		r.put(R_KEY_MSG, msg);
		return r;
	}

	public static ResponseMsg error(String msg) {
		return error(500, msg);
	}

	public static ResponseMsg error() {
		return error(500, "未知异常，请联系管理员");
	}

	public boolean isOk() {
		int code = (int) this.get(R_KEY_CODE);
		return code == 0;
	}

	public boolean isError() {
		int code = (int) this.get(R_KEY_CODE);
		return code != 0;
	}

	public Object getData() {
		return this.get(R_KEY_DATA);
	}

	public int getCode() {
		return (int) this.get(R_KEY_CODE);
	}

	public String getMsg() {
		return (String) this.get(R_KEY_MSG);
	}

	@Override
	public ResponseMsg put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
