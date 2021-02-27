package com.linuss.security.demo.springsecurityauthnauthz.services;

import com.linuss.security.demo.springsecurityauthnauthz.entities.Customer;
import com.linuss.security.demo.springsecurityauthnauthz.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service("oauth2authSuccessHandler")
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	RedirectStrategy redirect;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println("call success handler!");
		boolean isCustomer = customerRepo.existsCustomersByUsername(authentication.getName());
		if(!isCustomer) {
			Customer customer = new Customer();
			customer.setUsername(authentication.getName());
			customer.setVerified(true);
			customer.setEnabled(true);
			customer.setPassword("Dan123456@");
			customerRepo.save(customer);
		}
		this.redirect.sendRedirect(request, response, "/");
	}
}
