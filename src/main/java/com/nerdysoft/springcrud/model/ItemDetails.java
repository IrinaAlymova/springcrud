package com.nerdysoft.springcrud.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ItemDetails {
    private String itemName;
    private Integer itemCount;
}
