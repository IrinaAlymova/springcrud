package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.dto.OrderGetDTO;
import com.nerdysoft.springcrud.dto.OrderPostDTO;
import com.nerdysoft.springcrud.mappers.OrderMapper;
import com.nerdysoft.springcrud.model.OrderDetails;
import com.nerdysoft.springcrud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasAuthority('create_order')")
    @PostMapping
    public OrderGetDTO addNewOrder(@RequestBody OrderPostDTO orderPostDTO) {
        return orderMapper.toDTO(orderService.addOrder(orderMapper.toEntity(orderPostDTO)));
    }


    @PreAuthorize("hasAuthority('view_order')")
    @GetMapping("/{id}")
    public OrderGetDTO getOrderById(@PathVariable Long id) {
        return orderMapper.toDTO(orderService.getOrderById(id));
    }


    @PreAuthorize("hasAuthority('view_all_orders')")
    @GetMapping
    public List<OrderGetDTO> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }


    @PreAuthorize("hasAuthority('delete_order')")
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }


    @PreAuthorize("hasAuthority('view_stats')")
    @GetMapping("/details")
    public OrderDetails getAllOrdersDetails() {
        return orderService.getAllOrdersDetails();
    }


    @PreAuthorize("hasAuthority('view_stats')")
    @GetMapping("/details/{user_id}")
    public OrderDetails getAllOrdersDetails(@PathVariable Long user_id) {
        return orderService.getAllOrdersDetailsByUserId(user_id);
    }
}
