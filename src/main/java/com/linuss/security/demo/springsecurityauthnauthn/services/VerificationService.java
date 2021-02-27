package com.linuss.security.demo.springsecurityauthnauthn.services;

import com.linuss.security.demo.springsecurityauthnauthn.entities.Verification;
import com.linuss.security.demo.springsecurityauthnauthn.repository.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationService {

	private final ConfirmationTokenRepository repository;

	public Long getVerificationIdByUsername(String username) {
		Verification verification = repository.findByUsername(username);
		if(verification != null) {
			return verification.getId();
		}
		return null;
	}

	public Long createVerification(String username) {
		if (!repository.existsByUsername(username)) {
			Verification verification = new Verification(username);
			verification = repository.save(verification);
			return verification.getId();
		}
		return getVerificationIdByUsername(username);
	}

	public String getUsernameForId(Long id) {
		Optional<Verification> verification = Optional.of(repository.getOne(id));
		return verification.map(Verification::getUsername).orElse(null);
	}
}
