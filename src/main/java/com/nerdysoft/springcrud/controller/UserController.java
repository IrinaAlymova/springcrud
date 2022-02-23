package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.dto.AuthResponseDTO;
import com.nerdysoft.springcrud.dto.OrderGetDTO;
import com.nerdysoft.springcrud.dto.UserGetDTO;
import com.nerdysoft.springcrud.dto.UserPostDTO;
import com.nerdysoft.springcrud.mappers.OrderMapper;
import com.nerdysoft.springcrud.mappers.UserMapper;
import com.nerdysoft.springcrud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, OrderMapper orderMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }


    @PreAuthorize("hasAuthority('view_all_users')")
    @GetMapping
    public List<UserGetDTO> getAllUsers() {
        return userService.findAllUsers()
                .stream().map(userMapper::toDTO)
                .collect(Collectors.toList());
    }


    @PreAuthorize("hasAuthority('view_user')")
    @GetMapping("/{id}")
    public UserGetDTO getUserById(@PathVariable Long id) {
        return userMapper.toDTO(userService.getUserById(id));
    }


    @PreAuthorize("hasAuthority('view_user')")
    @GetMapping("/{id}/orders")
    public List<OrderGetDTO> getUserOrders(@PathVariable Long id) {
        return userService.getUserOrdersById(id).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }


    @PreAuthorize("permitAll()")
    @PostMapping("/add")
    public UserGetDTO addNewUser(@RequestBody UserPostDTO userPostDTO) {
        return userMapper.toDTO(userService.addNewUser(userMapper.toEntity(userPostDTO)));
    }


    @PreAuthorize("hasAuthority('delete_user')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @PreAuthorize("permitAll()")
    @PostMapping("/auth")
    public AuthResponseDTO authorizeUser(@RequestBody UserPostDTO userPostDTO) {
        return AuthResponseDTO.builder()
                .token(userService.authorizeUser(userPostDTO.getEmail(), userPostDTO.getPassword()))
                .build();
    }
}