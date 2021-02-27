package com.linuss.security.demo.springsecurityauthnauthz.services;

import com.linuss.security.demo.springsecurityauthnauthz.entities.Customer;
import com.linuss.security.demo.springsecurityauthnauthz.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	CustomerRepository customerRepo;

	@Value("${disableEmailVerification}")
	private boolean disableEmailVerification;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer customer =
			Optional.of(customerRepo.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException(username));
		if(disableEmailVerification) {
			customer.setVerified(true);
		}
		return User.withUsername(customer.getUsername()).password(customer.getPassword()).authorities(String.valueOf(customer.getRole())).disabled(!customer.isVerified()).build();
		}
	}
