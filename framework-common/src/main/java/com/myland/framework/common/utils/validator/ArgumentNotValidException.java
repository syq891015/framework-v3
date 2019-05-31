package com.myland.framework.common.utils.validator;

/**
 * 方法参数异常
 * ValidatorUtils.validate方法调用时报出此异常
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-08 18:24
 */
public class ArgumentNotValidException extends RuntimeException {
	public ArgumentNotValidException() {
	}

	public ArgumentNotValidException(String message) {
		super(message);
	}

	public ArgumentNotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArgumentNotValidException(Throwable cause) {
		super(cause);
	}

	public ArgumentNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
