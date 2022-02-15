package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.entity.User;
import com.nerdysoft.springcrud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getPageWithAllUsers(Model model) {
        List<User> users = userService.findAllUsers();
        logger.info("user list size: " + users.size());
        for (User user : users) {
            logger.info("user: " + user.getId() + " " + user.getEmail());
        }
        model.addAttribute("users", users);
        return "users";
    }
}