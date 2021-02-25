package com.linuss.security.demo.springsecurityauthnauthn.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String authority;

	public Authorities withUsername(String username) {
		this.username = username;
		return this;
	}

	public Authorities withAuthority(String authority) {
		this.authority = authority;
		return this;
	}

}
