package com.nerdysoft.springcrud.service;

import com.nerdysoft.springcrud.controller.UserController;
import com.nerdysoft.springcrud.entity.Order;
import com.nerdysoft.springcrud.entity.User;
import com.nerdysoft.springcrud.repository.UserRepository;
import com.nerdysoft.springcrud.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(roleService.getRoleByCode("USER")));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<Order> getUserOrdersById(Long id) {
        return getUserById(id).getOrders();
    }

    public String authorizeUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        logger.info("user from db to authorize: " + user.getEmail());
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            logger.info("login for email: " + email + " failed");
            return jwtProvider.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("login failed: " + email);
        }
    }
}
