package com.linuss.security.demo.springsecurityauthnauthn.validation;

import com.linuss.security.demo.springsecurityauthnauthn.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return  username != null && userRepo.findByUsername(username) == null;
	}
}
