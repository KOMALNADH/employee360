package com.pack.Employee360.UserRegistration.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Component
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
	private Long id;
	private String firstName;
	private String lastName;
	@NaturalId(mutable=true)
	private String email;
	private String password;
	@Transient
	private String conformPassword;
	private LocalDate createdOn;
	private boolean isEnabled=false;
	private String role;
	public User(String email,String password,
			boolean isEnabled, String role) {
		super();
	
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
		this.role = role;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
