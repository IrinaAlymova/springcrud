package com.nerdysoft.springcrud.service;

import com.nerdysoft.springcrud.entity.Item;
import com.nerdysoft.springcrud.entity.Order;
import com.nerdysoft.springcrud.entity.User;
import com.nerdysoft.springcrud.exceptions.DuplicateException;
import com.nerdysoft.springcrud.exceptions.LoginFailedException;
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

    public User addNewUser(User newUser) {
        User user = userRepository.findByEmail(newUser.getEmail());
        if (user != null) {
            logger.info("user with email: " + newUser.getEmail() + " already exists");
            throw new DuplicateException("such user already exists");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRoles(List.of(roleService.getRoleByCode("USER")));
        return userRepository.save(newUser);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.info("user with id: " + id + " not found");
                    return new IllegalArgumentException("user not found");
                });
    }

    public List<Order> getUserOrdersById(Long id) {
        return getUserById(id).getOrders();
    }

    public String authorizeUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return jwtProvider.generateToken(user.getEmail());
        } else {
            logger.info("login failed for email: " + email);
            throw new LoginFailedException("wrong email or password");
        }
    }
}
