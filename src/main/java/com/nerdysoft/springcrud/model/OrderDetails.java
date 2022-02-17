package com.nerdysoft.springcrud.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderDetails {
    private Integer allOrdersCount;
    private BigDecimal totalPriceOfAllOrders;
}
