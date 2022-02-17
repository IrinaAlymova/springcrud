package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.dto.OrderGetDTO;
import com.nerdysoft.springcrud.dto.OrderPostDTO;
import com.nerdysoft.springcrud.mappers.OrderMapper;
import com.nerdysoft.springcrud.model.OrderDetails;
import com.nerdysoft.springcrud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public OrderGetDTO addNewOrder(@RequestBody OrderPostDTO orderPostDTO) {
        return orderMapper.toDTO(orderService.addOrder(orderMapper.toEntity(orderPostDTO)));
    }

    @GetMapping("/{id}")
    public OrderGetDTO getOrderById(@PathVariable Long id) {
        return orderMapper.toDTO(orderService.getOrderById(id));
    }

    @GetMapping
    public List<OrderGetDTO> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/details")
    public OrderDetails getAllOrdersDetails() {
        return orderService.getAllOrdersDetails();
    }

    @GetMapping("/details/{user_id}")
    public OrderDetails getAllOrdersDetails(@PathVariable Long user_id) {
        return orderService.getAllOrdersDetailsByUserId(user_id);
    }
}
