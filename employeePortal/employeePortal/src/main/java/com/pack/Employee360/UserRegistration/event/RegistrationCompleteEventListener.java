package com.pack.Employee360.UserRegistration.event;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.pack.Employee360.UserRegistration.model.RegistrationCompleteEvent;
import com.pack.Employee360.UserRegistration.model.User;
import com.pack.Employee360.UserRegistration.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>{
	@Autowired
	private  JavaMailSender mailSender;
	private final UserService userService;
	
	private User theUser;
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		// TODO Auto-generated method stub
		//1. Get newly registered user
		theUser=event.getUser();
		//2. create a verification token
//		String verificationToken = UUID.randomUUID().toString();
		String verificationToken = generateRandomOtp();
		//3.save the verification token
		userService.saveUserVerificationToken(theUser,verificationToken);
		//4. Build the verification URL to send to user
		String url=event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
		//5.send the token
			try {
				sendVerificationEmail(verificationToken);
			}catch (MessagingException | UnsupportedEncodingException e) {
	            throw new RuntimeException(e);
			}
			log.info("Click the link to verify your registration :  {}", verificationToken);
	}
	 private String generateRandomOtp() {
	        Random random = new Random();
	        int otp = 100_000 + random.nextInt(900_000);
	        return String.valueOf(otp);
	    }
	public void sendVerificationEmail(String url) throws MessagingException,UnsupportedEncodingException{
		
		String subject="Happy Birthday";
		String senderName="TCS Technologies";
		String mailContent="<p>  Hi,"+theUser.getFirstName()+",</p>"
							+"<p>This mail is regarding your birthday</p>"
							+"<p>we are all happily wishing your birthday may with this birthday you will achieve all your dreams and goals"
							+ "as birthday gift we are planning to send your onboarding mail sooner than later</p>"
							+"<p>so, please click the below link and fill the details for smooth onboarding and to win exciting gifts as a birthday gift</p>"
							+"<p>your OTP is</p>"+url
							+"<a href=\"" +url+ "\">Click Here</a>"+
			                "<p> Thank you <br> for joining us";
		MimeMessage message=mailSender.createMimeMessage();
		var messageHelper = new MimeMessageHelper(message);
		messageHelper.setFrom("komalnadh.31@gmail.com", senderName);
		messageHelper.setTo(theUser.getEmail());
		messageHelper.setSubject(subject);
		messageHelper.setText(mailContent, true);
		mailSender.send(message);
	}

}
