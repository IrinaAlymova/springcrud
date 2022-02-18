package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.dto.UserPostDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
public class LoginController {

    @PostMapping
    public void login(@RequestBody UserPostDTO userPostDTO) {

    }

}
