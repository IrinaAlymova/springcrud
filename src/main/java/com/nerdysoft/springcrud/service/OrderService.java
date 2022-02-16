package com.nerdysoft.springcrud.service;

import com.nerdysoft.springcrud.entity.Item;
import com.nerdysoft.springcrud.entity.Order;
import com.nerdysoft.springcrud.entity.User;
import com.nerdysoft.springcrud.model.OrderDeatils;
import com.nerdysoft.springcrud.repository.ItemRepository;
import com.nerdysoft.springcrud.repository.OrderRepository;
import com.nerdysoft.springcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public Order addOrder() {
        Order order = new Order();
        return orderRepository.save(order);
    }

    public void assignOrderToUser(Long order_id, Long user_id) {
        Order order = orderRepository.getById(order_id);
        User user = userRepository.getById(user_id);
        order.setUser(user);
        userRepository.save(user);
    }
    public void addItemToOrder(Long order_id, Long item_id) {
        Order order = orderRepository.getById(order_id);
        List<Item> orderItems = order.getOrderItems();
        Item item = itemRepository.getById(item_id);
        orderItems.add(item);
        orderRepository.save(order);
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

    public OrderDeatils getAllOrdersDetailsByUserId(Long user_id) {
        BigDecimal totalPrice = orderRepository.getAllOrdersPriceByUserId(user_id);
        Integer totalCount = orderRepository.getAllOrdersCountByUserId(user_id);
        return OrderDeatils.builder()
                .totalPriceOfAllOrders(totalPrice)
                .allOrdersCount(totalCount)
                .build();
    }
}
