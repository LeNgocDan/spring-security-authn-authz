package com.linuss.security.demo.springsecurityauthnauthz.services;

import com.linuss.security.demo.springsecurityauthnauthz.entities.User;
import com.linuss.security.demo.springsecurityauthnauthz.repository.UserRepository;
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
