package com.pack.Employee360.UserRegistration.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pack.Employee360.UserRegistration.model.RegistrationRequest;
import com.pack.Employee360.UserRegistration.model.Token;
import com.pack.Employee360.UserRegistration.model.User;
import com.pack.Employee360.UserRegistration.repository.TokenRepository;
import com.pack.Employee360.UserRegistration.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service

public class UserServiceImpl implements UserService{
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepo;
	@Autowired
	TokenRepository tokenRepo;
	
	@Override
	public User save(RegistrationRequest registrationRequest) {
		User user = new User();
		user.setFirstName(registrationRequest.firstName());
		user.setLastName(registrationRequest.lastName());
		user.setEmail(registrationRequest.email());
		user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.password()));
		user.setRole("user");
		userRepo.save(user);
		return user;
	}

	@Override
	public void saveUserVerificationToken(User theUser, String verificationToken) {
		Token token=new Token(verificationToken,theUser);
		tokenRepo.save(token);
	}

	@Override
	public String validateToken(String token) {
		// TODO Auto-generated method stub
		
		Token theToken=tokenRepo.findByToken(token);
		if(theToken == null) {
			return "Invalid verification token";
		}else {
		User user=theToken.getUser();
		Calendar calender=Calendar.getInstance();
		if(theToken.getExpirationTime().getTime()-calender.getTime().getTime() <=0) {
			tokenRepo.delete(theToken);
			return "token already invalid";
		}
		user.setEnabled(true);
		userRepo.save(user);
		return "valid";
		}
	}

	@Override
	public User saved(User u) {
		System.out.println(u.getEmail());
		System.out.println(u.getPassword());
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		u.setRole("user");
		LocalDate date=LocalDate.now();
		u.setCreatedOn(date);
		return userRepo.save(u);
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> user=userRepo.findByEmail(email);
		if(user.isEmpty()) {
			return null;
		}
		else {
			return user.get();
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Optional<User> u=userRepo.findById(user.getId());
		User newUser=u.get();
		newUser.setRole(user.getRole());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		userRepo.save(newUser);
		
	}

}
