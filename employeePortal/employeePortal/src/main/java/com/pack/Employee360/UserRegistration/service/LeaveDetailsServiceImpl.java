package com.pack.Employee360.UserRegistration.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pack.Employee360.UserRegistration.model.LeaveDetails;
import com.pack.Employee360.UserRegistration.model.User;
import com.pack.Employee360.UserRegistration.repository.LeaveDetailsRepository;

@Service
public class LeaveDetailsServiceImpl implements LeaveDetailsService{
	@Autowired
	LeaveDetailsRepository ldRepo;
	@Autowired
	UserService userService;
	@Override
	public LeaveDetails save(LeaveDetails leaveDetails) {
		System.out.println(leaveDetails.getEndDate());
	    LocalDate date = LocalDate.now();
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
//	    String formattedDate = date.format(formatter);
//	    LocalDate d=LocalDate.parse(formattedDate);
	    leaveDetails.setAppliedOn(date.toString());
	    leaveDetails.setStatus("pending");
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User u=userService.findByEmail(email);
		leaveDetails.setUser(u);
		return ldRepo.save(leaveDetails);
	}

	@Override
	public List<LeaveDetails> findAll() {
		// TODO Auto-generated method stub
		return ldRepo.findAll();
	}

	@Override
	public List<LeaveDetails> findAllByUser() {
		List<LeaveDetails> list=ldRepo.findAll();
		List<LeaveDetails> listByUser=new ArrayList<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User u=userService.findByEmail(email);
		for(LeaveDetails ld:list) {
			if(ld.getUser().equals(u)) {
				listByUser.add(ld);
			}
		}
		return listByUser;
	}

}
