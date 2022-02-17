package com.nerdysoft.springcrud.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderPostDTO {
    private Long user_id;
    private List<Long> items_ids;
}
