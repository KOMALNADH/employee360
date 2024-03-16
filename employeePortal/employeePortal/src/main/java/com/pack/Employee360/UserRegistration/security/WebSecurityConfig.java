package com.pack.Employee360.UserRegistration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig  {
	
	 @Autowired
	    private UserDetailsService userDetailsService;

	 @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.cors()
	                .and().csrf().disable()
	                .authorizeHttpRequests()
	                .antMatchers("/registration","/home","/verifyOtp")
	                .permitAll()
	                .and()
	                .authorizeHttpRequests()
	                .antMatchers("/resources/**").permitAll()
	                .and()
	                .authorizeHttpRequests()
	                .antMatchers("/updateLeaveRequest","/updateLeaveRequest").hasAnyAuthority("user")
	                .and()
	                .authorizeHttpRequests()
	                .antMatchers("/deleteEmployee","/adminDesk").hasAnyAuthority("user")
	                .and()
	                .authorizeHttpRequests()
	                .antMatchers("/login")
	                .permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll().and()
                    .exceptionHandling().accessDeniedPage("/403")
                    .and()
                    .build()
                    ;
	    }
//	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//	    }
}