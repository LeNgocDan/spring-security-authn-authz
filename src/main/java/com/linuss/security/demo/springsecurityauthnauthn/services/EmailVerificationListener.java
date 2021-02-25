package com.linuss.security.demo.springsecurityauthnauthn.services;

import com.linuss.security.demo.springsecurityauthnauthn.event.UserRegistraitionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationListener implements ApplicationListener<UserRegistraitionEvent> {

	private JavaMailSender mailSender;
//	private final VerificationService verificationService;

	@Override
	public void onApplicationEvent(UserRegistraitionEvent event) {
		String username = event.getUser().getUsername();
		String email = event.getUser().getEmail();
//		String verificationId = verificationService.createVerification(username);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Account Verification");
//		message.setText("Accoutn activation link: " + verificationId);
		message.setTo(email);
		mailSender.send(message);
	}

}
