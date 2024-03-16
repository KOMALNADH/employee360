package com.pack.Employee360.UserRegistration.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pack.Employee360.UserRegistration.model.LeaveDetails;
import com.pack.Employee360.UserRegistration.model.RegistrationCompleteEvent;
import com.pack.Employee360.UserRegistration.model.Token;
import com.pack.Employee360.UserRegistration.model.User;
import com.pack.Employee360.UserRegistration.repository.TokenRepository;
import com.pack.Employee360.UserRegistration.repository.UserRepository;
import com.pack.Employee360.UserRegistration.service.LeaveDetailsService;
import com.pack.Employee360.UserRegistration.service.UserService;
import com.pack.Employee360.UserRegistration.validation.UserValidator;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	ApplicationEventPublisher Publisher;
	@Autowired
	TokenRepository tokenRepo;
	@Autowired
	UserValidator userValidator;
	@Autowired
	LeaveDetailsService ldService;
	
	@GetMapping("/")
	public String check(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User u=userService.findByEmail(email);
		model.addAttribute("user", u.getFirstName());
		model.addAttribute("role", u.getRole());
		List<LeaveDetails> list=ldService.findAllByUser();
		model.addAttribute("leaveList", list);
		
		int totalRequests=list.size();
		int approvedRequests=0;
		int rejectedRequests=0;
		int pendingRequests=0;
		for(LeaveDetails ld:list) {
			if(ld.getStatus().equalsIgnoreCase("pending")) {
				pendingRequests++;
			}else if(ld.getStatus().equalsIgnoreCase("approved")) {
				rejectedRequests++;
			}else if(ld.getStatus().equalsIgnoreCase("rejected")) {
				rejectedRequests++;
			}
		}
		model.addAttribute("totalRequests", totalRequests);
		model.addAttribute("approvedRequests", approvedRequests);
		model.addAttribute("pendingRequests", pendingRequests);
		model.addAttribute("rejectedRequests", rejectedRequests);
		return "home";
	}
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/login")
	public String login(Model model,String error,String logout,RedirectAttributes red) {
		if (error != null) 
    		
            model.addAttribute("error", "Your username and password is invalid.");
		
        if (logout !=null) 
        	model.addAttribute("message", "You have been logged out successfully.");      
        	
        return "login";
		
	}
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
		
	}
	@PostMapping("/registration")
	public String userRegistration(@ModelAttribute("userForm") User userForm, final HttpServletRequest request,BindingResult result) {
		userValidator.validate(userForm, result);
		if(result.hasErrors()) {
			return "registration";
		}
		User user=userService.saved(userForm);
		System.out.println("save");
		Publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
		return "redirect:/verifyOtp";
	}
	
	@GetMapping("/verifyEmail")
	public String verifyEmail(@RequestParam("token") String token) {
		System.out.println("to verify");
		Token theToken=tokenRepo.findByToken(token);
		if(theToken.getUser().isEnabled()) {
			System.out.println("token is already verified successfully");
			return "token is already verified successfully";
		}
		String verificationResult=userService.validateToken(token);
		if(verificationResult.equalsIgnoreCase("valid")) {
			return "redirect:/login";
		}
		System.out.println("invalid verification token");
		return "invalid verification token";
	}
	@GetMapping("/verifyOtp")
	public String verifyOtp(Model model) {
		model.addAttribute("otpForm", new Token());
		return "otpForm";
		
	}
	@PostMapping("/verifyOtp")
	public String otpVerification(@ModelAttribute("otpForm") Token token,Model model) {
		Token theToken=tokenRepo.findByToken(token.getToken());
		if(theToken !=null ) {
			if(theToken.getUser().isEnabled()) {
				System.out.println("token is already verified successfully");
				model.addAttribute("message", "token is already verified successfully");
				return "otpForm";
			}
		}
		
			String verificationResult=userService.validateToken(token.getToken());
			if(verificationResult.equalsIgnoreCase("valid")) {
				return "redirect:/login";
			}else {
				model.addAttribute("message", "invalid verification token");
				return "otpForm";
			}
		
	}
	public String applicationUrl(HttpServletRequest request) {
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
	
	@GetMapping("/adminDesk")
	public String getAdminDesk(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userService.findByEmail(email);
		model.addAttribute("user", user.getFirstName());
		model.addAttribute("role", user.getRole());
		
		List<User> list=userRepo.findAll();
		model.addAttribute("userList", list);
		int totalRegisterd=list.size();
		int totalManagers=0;
		int totalUsers=0;
		int totalAdmins=0;
		for(User u:list) {
			if(u.getRole().equalsIgnoreCase("admin")) {
				totalAdmins++;
			}else if(u.getRole().equalsIgnoreCase("user")) {
				totalUsers++;
			}else if(u.getRole().equalsIgnoreCase("manager")) {
				totalManagers++;
			}
		}
		model.addAttribute("totalRegistered", totalRegisterd);
		model.addAttribute("totalManagers", totalManagers);
		model.addAttribute("totalUsers", totalUsers);
		model.addAttribute("totalAdmins",totalAdmins);
		return "adminDesk";
		
	}
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable("id") Long id,Model model) {
		Optional<User> user=userRepo.findById(id);
		if(user.isPresent()) {
			model.addAttribute("userForm", user.get());
			return "updateEmployee";
		}
		return "redirect:/adminDesk";
		
	}
	@PostMapping("/updateEmployee")
	public String updateAndSaveEmployee(@ModelAttribute("userForm") User user) {
		userService.update(user);
		return "redirect:/adminDesk";
		
	}

}
