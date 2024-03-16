package com.pack.Employee360.UserRegistration.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
	
	private static final int EXPIRATION_TIME=15;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String token;
	private Date expirationTime;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Token(String token,User user) {
		super();
		this.token=token;
		this.user=user;
		this.expirationTime=this.getTokenExpirationTime();
	}
	public Token(String token) {
		super();
		this.token=token;
		this.expirationTime=this.getTokenExpirationTime();
	}
	public Date getTokenExpirationTime() {
		Calendar calender=Calendar.getInstance();
		calender.setTimeInMillis(new Date().getTime());
		calender.add(calender.MINUTE, EXPIRATION_TIME);;
		return new Date(calender.getTime().getTime());
	}
}
