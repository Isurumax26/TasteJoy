package com.tastejoy.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tastejoy.app.dao.impl.UserDAOImpl;
import com.tastejoy.app.entity.RegistrationForm;
import com.tastejoy.app.entity.Role;
import com.tastejoy.app.entity.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
    private UserDAOImpl userDAO;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public RegistrationController(PasswordEncoder passwordEncoder) {
		 this.passwordEncoder = passwordEncoder;
	 }
	
	 @GetMapping
	 public String registerForm() {
		 return "registration";
	 }
	 
	 @PostMapping
	 public String processRegistration(RegistrationForm form) {
		 userDAO.add(form.toUser(passwordEncoder));
		 return "redirect:/login";
	 }
	
	
}
