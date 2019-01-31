package com.myland.framework.web.config;

import com.myland.framework.common.message.ResponseMsg;
import com.myland.framework.web.utils.validator.ArgumentNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 异常处理
 *
 * @author SunYanQing
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

	/**
	 * 全局异常捕捉处理
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseMsg handleException(Exception e) {
		log.error(e.getMessage(), e);

		BindingResult result = null;
		if (e instanceof MethodArgumentNotValidException) {
			result = ((MethodArgumentNotValidException) e).getBindingResult();
		} else if (e instanceof BindException) {
			result = ((BindException) e).getBindingResult();
		} else if (e instanceof MissingServletRequestParameterException) {
			String paramName = ((MissingServletRequestParameterException) e).getParameterName();

			return ResponseMsg.error("'" + paramName + "' 缺失");
		} else if (e instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();

			return ResponseMsg.error(constraintViolations.iterator().next().getMessage());
		} else if (e instanceof ArgumentNotValidException) {
			return ResponseMsg.error(e.getMessage());
		}
		if (result != null) {
			List<ObjectError> errors = result.getAllErrors();
			String tips = "参数不合法";
			if (errors.size() > 0) {
				tips = errors.get(0).getDefaultMessage();
			}
			return ResponseMsg.error(tips);
		}
		return ResponseMsg.error("系统异常");
	}

}