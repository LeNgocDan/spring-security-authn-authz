package com.linuss.security.demo.springsecurityauthnauthz.repository;

import com.linuss.security.demo.springsecurityauthnauthz.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByEmail(String email);
	Customer findByUsername(String username);

}
