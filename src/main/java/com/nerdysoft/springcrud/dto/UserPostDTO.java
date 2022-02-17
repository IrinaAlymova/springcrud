package com.nerdysoft.springcrud.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class UserPostDTO {
    private String email;
    private String password;
}
