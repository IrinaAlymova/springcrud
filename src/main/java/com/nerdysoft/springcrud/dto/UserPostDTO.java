package com.nerdysoft.springcrud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPostDTO {
    private String email;
    private String password;
}
