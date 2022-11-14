package com.tastejoy.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tastejoy.app.dao.UserDAO;
import com.tastejoy.app.dao.impl.UserDAOImpl;
import com.tastejoy.app.entity.User;

public class UserRepositoryUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAOImpl userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.get(username);
		if (user != null) {
			 return user;
		}
		throw new UsernameNotFoundException(
			 "User '" + username + "' not found");
		
	}
}
