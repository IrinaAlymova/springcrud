package com.nerdysoft.springcrud.service;

import com.nerdysoft.springcrud.entity.Order;
import com.nerdysoft.springcrud.model.OrderDetails;
import com.nerdysoft.springcrud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
        //TODO: add exception
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    public OrderDetails getAllOrdersDetails() {
        BigDecimal totalPrice = orderRepository.getAllOrdersPrice();
        Integer totalCount = orderRepository.getAllOrdersCount();
        return OrderDetails.builder()
                .totalPriceOfAllOrders(totalPrice)
                .allOrdersCount(totalCount)
                .build();
    }

    public OrderDetails getAllOrdersDetailsByUserId(Long user_id) {
        BigDecimal totalPrice = orderRepository.getAllOrdersPriceByUserId(user_id);
        Integer totalCount = orderRepository.getAllOrdersCountByUserId(user_id);
        return OrderDetails.builder()
                .totalPriceOfAllOrders(totalPrice)
                .allOrdersCount(totalCount)
                .build();
    }
}
