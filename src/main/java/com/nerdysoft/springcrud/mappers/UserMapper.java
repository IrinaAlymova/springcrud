package com.nerdysoft.springcrud.mappers;

import com.nerdysoft.springcrud.dto.UserGetDTO;
import com.nerdysoft.springcrud.dto.UserPostDTO;
import com.nerdysoft.springcrud.entity.Role;
import com.nerdysoft.springcrud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserPostDTO userPostDTO) {
        return User.builder()
                .email(userPostDTO.getEmail())
                .password(passwordEncoder.encode(userPostDTO.getPassword()))
                .role(Role.USER)
                .build();
    }

    public UserGetDTO toDTO(User user) {
        return UserGetDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
