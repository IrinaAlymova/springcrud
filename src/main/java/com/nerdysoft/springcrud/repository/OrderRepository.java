package com.nerdysoft.springcrud.repository;

import com.nerdysoft.springcrud.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
