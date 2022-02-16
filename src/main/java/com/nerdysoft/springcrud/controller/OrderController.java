package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.entity.Item;
import com.nerdysoft.springcrud.entity.Order;
import com.nerdysoft.springcrud.model.OrderDeatils;
import com.nerdysoft.springcrud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order addNewOrder() {
        return orderService.addOrder();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/details")
    public OrderDeatils getAllOrdersDetails() {
        return orderService.getAllOrdersDetails();
    }

    @GetMapping("/details/{user_id}")
    public OrderDeatils getAllOrdersDetails(@PathVariable Long user_id) {
        return orderService.getAllOrdersDetailsByUserId(user_id);
    }

    @PutMapping("/{order_id}/users/{user_id}")
    public void assignOrderToUser(@PathVariable Long order_id, @PathVariable Long user_id) {
        orderService.assignOrderToUser(order_id, user_id);
    }

    @PutMapping("/{order_id}/items/{item_id}")
    public void addItemToOrder(@PathVariable Long order_id, @PathVariable Long item_id) {
        orderService.addItemToOrder(order_id, item_id);
    }
}
