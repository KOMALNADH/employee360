package com.pack.Employee360.UserRegistration.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.pack.Employee360.UserRegistration.model.LeaveDetails;
import com.pack.Employee360.UserRegistration.model.User;
import com.pack.Employee360.UserRegistration.repository.LeaveDetailsRepository;
import com.pack.Employee360.UserRegistration.service.LeaveDetailsService;

@Controller
public class UserDataController {
	@Autowired
	LeaveDetailsService ldService;
	@Autowired
	LeaveDetailsRepository ldRepo;
	
	
	@GetMapping("/addLeaveRequest")
	public String getData(Model model) {
		model.addAttribute("leaveForm", new LeaveDetails());
		return "addLeaveRequest";
	}
	@PostMapping("/addLeaveRequest")
	public String addleaveRequest(@ModelAttribute("leaveForm") LeaveDetails leaveForm,Model model) {
		System.out.println(leaveForm.getEndDate());
		ldService.save(leaveForm);
		List<LeaveDetails> list=ldService.findAll();
		model.addAttribute("leaveList", list);
		return "redirect:/";
		
	}
	@GetMapping("/deleteLeaveRequest/{eid}")
	public String deleteById(@PathVariable("eid") Integer eid) {
		if(ldRepo.findById(eid).isPresent()) {
			ldRepo.deleteById(eid);
			return "redirect:/";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/updateLeaveRequest/{eid}")
	public String updateById(@PathVariable("eid") Integer eid,Model model) {
		Optional<LeaveDetails> ld=ldRepo.findById(eid);
		model.addAttribute("leaveForm", ld);
		return "editLeaveForm";
		
	}
	@PostMapping("/updateLeaveRequest")
	public String updateLeaveRequest(@ModelAttribute("leaveForm") LeaveDetails ld) {
		ldService.save(ld);
		return "redirect:/";
		
	}
	@GetMapping("/403")
	public String accessDenied() {
		return "accessDenied";
	}
}
