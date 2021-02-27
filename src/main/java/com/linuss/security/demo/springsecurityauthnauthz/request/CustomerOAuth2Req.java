package com.linuss.security.demo.springsecurityauthnauthz.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter @Setter
public class CustomerOAuth2Req {
	private String lastName;
	private String firstName;
	private String username;
	private String email;

	public CustomerOAuth2Req(String username, String email) {
		this.username = username;
		this.email = email;
	}
}
