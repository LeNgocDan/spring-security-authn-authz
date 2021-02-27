package com.linuss.security.demo.springsecurityauthnauthz.config;

import com.linuss.security.demo.springsecurityauthnauthz.services.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.annotation.Resource;

@Configuration
@RequiredArgsConstructor
public class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	UserDetailsService userDetailsService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private OAuth2AuthenticationSuccessHandler authSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().mvcMatchers("/login", "/register", "/h2-console", "/confirm", "/verify/email/**").permitAll().antMatchers(
			"/admin").hasRole("Admin").
			anyRequest().authenticated().
			and().formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error").
			and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("remember-me").
			and().
			oauth2Login().loginPage("/login").successHandler(authSuccessHandler) .
			and().
			exceptionHandling().accessDeniedPage("/403");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public RedirectStrategy getRedirectStrategy() {
		return new DefaultRedirectStrategy();
	}
}
