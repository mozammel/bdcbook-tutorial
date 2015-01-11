package com.bdcyclists.bdcbook.validation;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidatorContext;

public class ValidatorUtil {

	public static Object getFieldValue(Object object, String fieldName)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field f = object.getClass().getDeclaredField(fieldName);
		f.setAccessible(true);
		return f.get(object);
	}

	public static void addValidationError(String field,
			ConstraintValidatorContext context) {
		context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
			.addPropertyNode(field)
			.addConstraintViolation();
		
	}

}
