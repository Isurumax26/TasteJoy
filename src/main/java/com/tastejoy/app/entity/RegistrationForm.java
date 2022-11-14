package com.tastejoy.app.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistrationForm {
	private String username;
	private String password;
	private String firstname;
	private String secondname;
	private String street;
	private String state;
	private String email;
	private String phone;
	 
	public User toUser(PasswordEncoder passwordEncoder) {
		User user = new User();
		System.out.println(username);
		System.out.println(firstname);
		System.out.println(password);
		user.setUsername(username);
		user.setPassword(password);
		user.setAddress(street + state);
		user.setPhoneNumber(phone);
		user.setEmail(email);
		user.setFirstName(firstname);
		user.setSecondName(secondname);
		return user;
	}

}
