package com.linuss.security.demo.springsecurityauthnauthz.validation;

import com.linuss.security.demo.springsecurityauthnauthz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	@Autowired
	UserRepository userRepo;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return email != null && userRepo.findByEmail(email) == null;
	}
}
