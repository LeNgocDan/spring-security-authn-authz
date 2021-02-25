package com.linuss.security.demo.springsecurityauthnauthn;

import com.linuss.security.demo.springsecurityauthnauthn.entities.Authorities;
import com.linuss.security.demo.springsecurityauthnauthn.entities.User;
import com.linuss.security.demo.springsecurityauthnauthn.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityAuthnAuthnApplication implements CommandLineRunner {

	@Autowired
	UserServices services;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthnAuthnApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("dan", passwordEncoder.encode("pass"), "dan@gmail.com", true);
		User user_2 = new User("thang", passwordEncoder.encode("pass"), "thang@gmail.com", true);
		user.withRole(new Authorities().withUsername("dan").withAuthority("ROLE_User")); services.saveUser(user);
		user_2.withRole(new Authorities().withUsername("thu").withAuthority("ROLE_Admin")); services.saveUser(user_2);
	}
}
