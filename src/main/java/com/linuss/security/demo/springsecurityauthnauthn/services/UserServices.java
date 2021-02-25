package com.linuss.security.demo.springsecurityauthnauthn.services;

import com.linuss.security.demo.springsecurityauthnauthn.entities.User;
import com.linuss.security.demo.springsecurityauthnauthn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
	@Autowired
	UserRepository repo;

	public User saveUser(User user) {
		return repo.save(user);
	}

}
