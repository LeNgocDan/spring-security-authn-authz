package com.linuss.security.demo.springsecurityauthnauthn.repository;

import com.linuss.security.demo.springsecurityauthnauthn.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByEmail(String email);
	Customer findByUsername(String username);

}
