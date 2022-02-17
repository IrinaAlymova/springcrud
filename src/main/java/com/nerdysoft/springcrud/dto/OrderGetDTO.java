package com.nerdysoft.springcrud.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderGetDTO {
    private Long id;
    private UserGetDTO user;
    private List<ItemGetDTO> orderItems;
}
