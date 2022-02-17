package com.nerdysoft.springcrud.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemPostDTO {
    private String name;
    private BigDecimal price;
}
