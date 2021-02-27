package com.linuss.security.demo.springsecurityauthnauthz;

import com.linuss.security.demo.springsecurityauthnauthz.entities.Authorities;
import com.linuss.security.demo.springsecurityauthnauthz.entities.Customer;
import com.linuss.security.demo.springsecurityauthnauthz.entities.User;
import com.linuss.security.demo.springsecurityauthnauthz.repository.CustomerRepository;
import com.linuss.security.demo.springsecurityauthnauthz.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityAuthnAuthzApplication implements CommandLineRunner {

	@Autowired
	UserServices services;

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthnAuthzApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("dan", passwordEncoder.encode("pass"), "dan@gmail.com", true);
		User user_2 = new User("thang", passwordEncoder.encode("pass"), "thang@gmail.com", true);
		user.withRole(new Authorities().withUsername("dan").withAuthority("ROLE_User")); services.saveUser(user);
		user_2.withRole(new Authorities().withUsername("thang").withAuthority("ROLE_Admin")); services.saveUser(user_2);

		Customer customer_1 = new Customer("ngocdan", passwordEncoder.encode("pass"), "ngocdan@gmail.com");
		customer_1.setEnabled(true);
		customer_1.setVerified(true);
		customerRepo.save(customer_1);

	}
}
