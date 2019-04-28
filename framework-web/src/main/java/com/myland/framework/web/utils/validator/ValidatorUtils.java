package com.myland.framework.web.utils.validator;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * @author SunYanQing
 */
public class ValidatorUtils {
	private static Validator validator;

	static {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
				.configure()
				.failFast(true)
				.buildValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	/**
	 * 校验对象
	 *
	 * @param object 待校验对象
	 * @param groups 待校验的组
	 */
	public static void validate(Object object, Class... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
			throw new ArgumentNotValidException(constraint.getMessage());
		}
	}

	/**
	 * 校验对象的某一个属性
	 * @param obj 对象
	 * @param propertyName 属性名
	 * @param groups 校验组
	 * @param <T> 对象的类型
	 */
	public static <T>  void validateProperty(T obj, String propertyName, Class... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty(obj, propertyName, groups);
		if (!constraintViolations.isEmpty()) {
			ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
			throw new ArgumentNotValidException(constraint.getMessage());
		}
	}
}
