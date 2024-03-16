package com.pack.Employee360.UserRegistration.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.pack.Employee360.UserRegistration.model.User;

import lombok.Data;

@Data
@Component
public class UserRegistrationDetails  implements UserDetails{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8178906610319572246L;
	private String userName;
	private String password;
	private boolean isEnabled;
	private List<GrantedAuthority> authorities ;
	
	public UserRegistrationDetails(User user) {
		this.userName=user.getEmail();
		this.password=user.getPassword();
		this.isEnabled=user.isEnabled();
		this.authorities=(List<GrantedAuthority>) getAuthorities();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("user"));
        return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}

}
