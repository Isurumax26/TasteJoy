package com.tastejoy.app.controller;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tastejoy.app.dao.impl.UserDAOImpl;
import com.tastejoy.app.entity.User;

@Controller
@RequestMapping("/users")
public class UserController {
	

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private static final String USER_VIEW = "user";
    private static final String USERS_VIEW = "users";
    private static final String REDIRECT_INDEX = "redirect:/users/";

    @Autowired
    private UserDAOImpl userDAO;

    @GetMapping({"/{username}"})
    public String getUser(@PathVariable(required = false) String username, Model model) {
        model.addAttribute("users", userDAO.get());
        model.addAttribute("user", username != null ? userDAO.get(username) : new User());
        return USER_VIEW;
    }
    
    @GetMapping
    public String getUser(Model model) {
        model.addAttribute("users", userDAO.get());
        return USERS_VIEW;
    }


    @PostMapping
    public ModelAndView saveUser(User user) {
        if (StringUtils.isNotBlank(user.getUsername()))
            userDAO.updateUser(user);
        return new ModelAndView(REDIRECT_INDEX + user.getUsername());
    }

    @PutMapping("/{username}")
    public ModelAndView updateUser(User user) {
        userDAO.updateUser(user);
        return new ModelAndView(REDIRECT_INDEX + user.getUsername());
    }

    @GetMapping("/delete/{username}")
    public ModelAndView deleteUser(@PathVariable String username) {
        LOGGER.info("Deleting user: {}", username);
        userDAO.delete(username);
        return new ModelAndView(REDIRECT_INDEX);
    }

}
