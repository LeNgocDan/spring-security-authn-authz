package com.linuss.security.demo.springsecurityauthnauthn.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter @Getter @NoArgsConstructor
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Please enter your username")
	private String username;
	@NotEmpty(message="Please enter in a password")
	private String password;
	@NotEmpty(message="Please enter an email")
	@Email
	private String email;
	private boolean enabled;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Authorities> roles;

	public User(String username, String password, String email, boolean enabled) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
	}

	public User withRole(Authorities role) {
		if(roles == null) roles = new HashSet<>();
		roles.add(role);
		return this;
	}
}
