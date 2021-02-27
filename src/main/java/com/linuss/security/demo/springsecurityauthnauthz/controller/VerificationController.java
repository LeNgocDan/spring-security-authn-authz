package com.linuss.security.demo.springsecurityauthnauthz.controller;

import com.linuss.security.demo.springsecurityauthnauthz.entities.Customer;
import com.linuss.security.demo.springsecurityauthnauthz.repository.CustomerRepository;
import com.linuss.security.demo.springsecurityauthnauthz.services.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VerificationController {
	@Autowired
	private VerificationService verificationService;

	@Autowired
	private CustomerRepository customerRepo;

	@GetMapping("/verify/email")
	public String verifyEmail(@RequestParam Long id) {
		String username = verificationService.getUsernameForId(id);
		if(username != null) {
			Customer customer = customerRepo.findByUsername(username);
			customer.setVerified(true);
			customerRepo.save(customer);
		}
//		return "redirect:/login-verified";
				return "redirect:/login";
	}
}
