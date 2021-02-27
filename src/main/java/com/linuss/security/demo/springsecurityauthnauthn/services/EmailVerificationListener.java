package com.linuss.security.demo.springsecurityauthnauthn.services;

import com.linuss.security.demo.springsecurityauthnauthn.event.UserRegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VerificationService verificationService;

	@Override
	public void onApplicationEvent(UserRegistrationEvent event) {
		String username = event.getCustomer().getUsername();
		Long verificationId = verificationService.createVerification(username);
		String email = event.getCustomer().getEmail();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Account Verification");
		message.setText("Account activation link: http://localhost:8080/verify/email?id="+verificationId);
		message.setTo(email);
		mailSender.send(message);
	}

}
