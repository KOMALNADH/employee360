package com.pack.Employee360.UserRegistration.service;

import com.pack.Employee360.UserRegistration.model.RegistrationRequest;
import com.pack.Employee360.UserRegistration.model.User;

public interface UserService {
	
	User save(RegistrationRequest request);
	void saveUserVerificationToken(User theUser,String verificationToken);
	String validateToken(String token);
	User findByEmail(String email);
	User saved(User u);
	void update(User user);
	
}
