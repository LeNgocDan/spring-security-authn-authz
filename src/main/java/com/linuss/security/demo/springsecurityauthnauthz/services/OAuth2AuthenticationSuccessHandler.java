package com.linuss.security.demo.springsecurityauthnauthz.services;

import com.linuss.security.demo.springsecurityauthnauthz.entities.Customer;
import com.linuss.security.demo.springsecurityauthnauthz.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Service("oauth2authSuccessHandler")
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
			OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
			Map<String, Object> attributes = token.getPrincipal().getAttributes();
			Customer customer = new Customer();
			String name = attributes.get("name").toString();
			String firstName = name.split(" ")[0];
			String lastName = name.split(" ")[1];
			String email = attributes.get("email").toString();
			customer.setUsername(name);
			customer.setEmail(email);
			customer.setVerified(true);
			customer.setEnabled(true);
			customer.setPassword("Dan123456@");
			customerRepo.save(customer);
		}
		this.redirect.sendRedirect(request, response, "/");
	}
}
