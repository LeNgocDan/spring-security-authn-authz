package com.linuss.security.demo.springsecurityauthnauthn.validation;

import com.linuss.security.demo.springsecurityauthnauthn.request.UserRegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object> {

	@Override
	public boolean isValid(Object user, ConstraintValidatorContext context) {
		String password = ((UserRegisterRequest) user).getPassword();
		String confirmedPassword = ((UserRegisterRequest) user).getConfirmPassword();
		return password.equals(confirmedPassword);
	}
}
