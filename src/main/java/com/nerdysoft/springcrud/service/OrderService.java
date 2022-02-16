package com.nerdysoft.springcrud.service;

import com.nerdysoft.springcrud.entity.Item;
import com.nerdysoft.springcrud.entity.Order;
import com.nerdysoft.springcrud.model.OrderDeatils;
import com.nerdysoft.springcrud.repository.ItemRepository;
import com.nerdysoft.springcrud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public Order addOrder(Order newOrder) {
        return orderRepository.save(newOrder);
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

    public OrderDeatils getAllOrdersDetails() {
        BigDecimal totalPrice = orderRepository.getAllOrdersPrice();
        Integer totalCount = orderRepository.getAllOrdersCount();
        return OrderDeatils.builder()
                .totalPriceOfAllOrders(totalPrice)
                .allOrdersCount(totalCount)
                .build();
    }
}
