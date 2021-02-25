package com.linuss.security.demo.springsecurityauthnauthn.event;

import com.linuss.security.demo.springsecurityauthnauthn.entities.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserRegistraitionEvent extends ApplicationEvent {
	private final User user;

	public UserRegistraitionEvent(User user) {
		super(user);
		this.user = user;
	}
}
