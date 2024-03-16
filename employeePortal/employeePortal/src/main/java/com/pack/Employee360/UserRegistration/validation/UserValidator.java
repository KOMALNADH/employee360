package com.pack.Employee360.UserRegistration.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pack.Employee360.UserRegistration.model.User;
import com.pack.Employee360.UserRegistration.repository.UserRepository;
import com.pack.Employee360.UserRegistration.service.UserService;


@Component
public class UserValidator implements Validator{
	
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;
	
	@Override
	public boolean supports(Class<?> aClass) {
		// TODO Auto-generated method stub
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        if (user.getFirstName().length() < 4 || user.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Size.userForm.firstName");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        if (user.getLastName().length() < 4 || user.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Size.userForm.lastName");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail()!=null) {
        	int c=0;
    		for(int i=0;i<user.getEmail().length();i++){
    		    if(user.getEmail().charAt(i)=='@'){
    		        c=i;
    		        break;
    		    }
    		}
        	if(!user.getEmail().substring(c).equals("@gmail.com")) {
        		errors.rejectValue("email", "Format.userForm.email");
        	}
//        	Optional<User> u=userRepo.findByEmail(user.getEmail());
//        	if(u.get() !=null) {
//        		errors.rejectValue("email", "Duplicate.userForm.email");
//        	}
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConformPassword().equals(user.getPassword())) {
            errors.rejectValue("conformPassword", "Diff.userForm.conformPassword");
        }
		
	}

}
