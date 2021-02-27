package com.linuss.security.demo.springsecurityauthnauthz.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
@Setter @Getter @NoArgsConstructor
public class Customer implements Serializable {

	static public enum Role {ROLE_User, ROLE_Admin};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String email;

	private boolean isVerified;
	private boolean isEnabled = false;

	@Enumerated(EnumType.STRING)
	private Role role = Role.ROLE_User;

	public Customer(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
