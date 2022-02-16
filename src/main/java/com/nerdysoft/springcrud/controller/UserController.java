package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.entity.Item;
import com.nerdysoft.springcrud.entity.Order;
import com.nerdysoft.springcrud.entity.User;
import com.nerdysoft.springcrud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/orders")
    public List<Order> getUserOrders(@PathVariable Long id) {
        return userService.getUserOrdersById(id);
    }

    @PostMapping
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}