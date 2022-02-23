package com.nerdysoft.springcrud.repository;

import com.nerdysoft.springcrud.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT SUM(i.price) FROM orders_items oi LEFT JOIN items i ON oi.item_id = i.id",
            nativeQuery = true)
    BigDecimal getAllOrdersPrice();

    @Query(value = "SELECT COUNT(o.id) FROM orders o",
            nativeQuery = true)
    Integer getAllOrdersCount();

    @Query(value = "SELECT SUM(i.price) FROM orders_items oi LEFT JOIN items i ON oi.item_id = i.id WHERE oi.order_id in (SELECT o.id FROM orders o WHERE o.user_id = ?1)",
            nativeQuery = true)
    BigDecimal getAllOrdersPriceByUserId(Long user_id);

    @Query(value = "SELECT COUNT(o.id) FROM orders o WHERE o.user_id = ?1",
            nativeQuery = true)
    Integer getAllOrdersCountByUserId(Long user_id);
}
