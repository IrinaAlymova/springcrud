package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.entity.User;
import com.nerdysoft.springcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/signup")
    String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping(value = "/signup")
    String registerUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "users";
    }
}
