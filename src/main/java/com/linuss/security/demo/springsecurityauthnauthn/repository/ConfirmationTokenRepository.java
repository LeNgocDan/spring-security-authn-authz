package com.linuss.security.demo.springsecurityauthnauthn.repository;

import com.linuss.security.demo.springsecurityauthnauthn.entities.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<Verification, Long> {
	Verification findByUsername(String username);
	boolean existsByUsername(String username);
}
