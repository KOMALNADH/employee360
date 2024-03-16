package com.pack.Employee360.UserRegistration.service;

import java.util.List;

import com.pack.Employee360.UserRegistration.model.LeaveDetails;

public interface LeaveDetailsService {
	
	LeaveDetails save(LeaveDetails leaveDetails);

	List<LeaveDetails> findAll();

	List<LeaveDetails> findAllByUser();
}
