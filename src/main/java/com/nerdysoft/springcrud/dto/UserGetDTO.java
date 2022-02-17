package com.nerdysoft.springcrud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGetDTO {
    private Long id;
    private String email;
}