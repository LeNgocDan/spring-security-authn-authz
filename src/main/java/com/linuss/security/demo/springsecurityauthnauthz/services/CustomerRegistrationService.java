package com.linuss.security.demo.springsecurityauthnauthz.services;

import com.linuss.security.demo.springsecurityauthnauthz.entities.Customer;
import com.linuss.security.demo.springsecurityauthnauthz.repository.CustomerRepository;
import com.linuss.security.demo.springsecurityauthnauthz.request.CustomerOAuth2Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerRegistrationService {

	@Autowired
	CustomerRepository customerRepo;


	public void registerNewCustomerOAuth2(CustomerOAuth2Req customerOAuth2Req) {
		Customer customer = new Customer();
		customer.setUsername(customerOAuth2Req.getUsername());
		customer.setEmail(customerOAuth2Req.getEmail());
		customer.setEnabled(true);
		customer.setVerified(true);
		customerRepo.save(customer);
	}
}
