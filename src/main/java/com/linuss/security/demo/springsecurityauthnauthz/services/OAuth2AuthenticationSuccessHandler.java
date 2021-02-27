package com.linuss.security.demo.springsecurityauthnauthz.services;

import com.linuss.security.demo.springsecurityauthnauthz.entities.Customer;
import com.linuss.security.demo.springsecurityauthnauthz.repository.CustomerRepository;
import com.linuss.security.demo.springsecurityauthnauthz.request.CustomerOAuth2Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

	@Autowired
	CustomerRegistrationService customerRegistrationService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println("call success handler!");
		boolean isCustomer = customerRepo.existsCustomersByUsername(authentication.getName());
		if(!isCustomer) {
			OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
			Map<String, Object> attributes = token.getPrincipal().getAttributes();
			String name = attributes.get("name").toString();
			String firstName = name.split(" ")[0];
			String lastName = name.split(" ")[1];
			String email = attributes.get("email").toString();
			CustomerOAuth2Req customer = new CustomerOAuth2Req(name,  email);
			customerRegistrationService.registerNewCustomerOAuth2(customer);
		}
		this.redirect.sendRedirect(request, response, "/");
	}

	/**
	 @Override
	 public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	 Authentication authentication) throws IOException, ServletException {
	 if(!this.portfolioService.userHasAportfolio(authentication.getName())) {
	 this.portfolioService.createNewPortfolio(authentication.getName());
	 OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
	 Map<String, Object> attributes = token.getPrincipal().getAttributes();
	 String firstname = null, lastname = null, email = null;
	 if(token.getAuthorizedClientRegistrationId().equals("facebook")) {
	 String name = attributes.get("name").toString();
	 firstname = name.split(" ")[0];
	 lastname = name.split(" ")[1];
	 email = attributes.get("email").toString();
	 } else if (token.getPrincipal() instanceof DefaultOidcUser) {
	 DefaultOidcUser oidcToken = (DefaultOidcUser) token.getPrincipal();
	 firstname = oidcToken.getGivenName();
	 lastname = oidcToken.getFamilyName();
	 email = oidcToken.getEmail();
	 }
	 UserOAuth2Dto user = new UserOAuth2Dto(firstname,lastname,authentication.getName(),email);
	 this.userRegistrationService.registerNewAuth2User(user);
	 }
	 this.redirectStrategy.sendRedirect(request, response, "/portfolio");
	 }
	 **/
}
