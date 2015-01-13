package com.bdcyclists.bdcbook.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordNotEqualValidator implements ConstraintValidator<PasswordNotEqual, Object> {

	private String passwordFieldName;
	
	private String passwordConfirmedFieldName;
	
	@Override
	public void initialize(PasswordNotEqual constraintAnnotation) {
		this.passwordFieldName = constraintAnnotation.passwordFieldName();
		this.passwordConfirmedFieldName = constraintAnnotation.passwordConfirmedFieldName();
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		
		try {
			String password = (String) ValidatorUtil.getFieldValue(value, passwordFieldName);
			String passwordVerification = (String) ValidatorUtil.getFieldValue(value, passwordConfirmedFieldName);
			
			if(!password.equals(passwordVerification)) {
				ValidatorUtil.addValidationError(passwordFieldName, context);
				ValidatorUtil.addValidationError(passwordConfirmedFieldName, context);
				
				return false;
			}
			
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
