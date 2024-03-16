package com.pack.Employee360.UserRegistration.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pack.Employee360.UserRegistration.model.User;
import com.pack.Employee360.UserRegistration.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService{
	
	private final UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> userOptional=userRepo.findByEmail(email);
		if(userOptional.isPresent()) {
			User user=userOptional.get();
			UserRegistrationDetails userRegistrationDetails=new UserRegistrationDetails(user);
			return new org.springframework.security.core.userdetails.User(userRegistrationDetails.getUsername(),userRegistrationDetails.getPassword(),userRegistrationDetails.getAuthorities());
		}
		else {
			throw new UsernameNotFoundException("User not found");
		}
	}

}
