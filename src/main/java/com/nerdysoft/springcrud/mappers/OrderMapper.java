package com.nerdysoft.springcrud.mappers;

import com.nerdysoft.springcrud.dto.OrderGetDTO;
import com.nerdysoft.springcrud.dto.OrderPostDTO;
import com.nerdysoft.springcrud.entity.Item;
import com.nerdysoft.springcrud.entity.Order;
import com.nerdysoft.springcrud.entity.User;
import com.nerdysoft.springcrud.service.ItemService;
import com.nerdysoft.springcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final UserService userService;
    private final ItemService itemService;
    private final UserMapper userMapper;
    private final ItemMapper itemMapper;

    @Autowired
    public OrderMapper(UserService userService, ItemService itemService, UserMapper userMapper, ItemMapper itemMapper) {
        this.userService = userService;
        this.itemService = itemService;
        this.userMapper = userMapper;
        this.itemMapper = itemMapper;
    }

    public Order toEntity(OrderPostDTO orderPostDTO) {
        User user = userService.getUserById(orderPostDTO.getUser_id());
        List<Item> items = orderPostDTO.getItems_ids().stream()
                .map(itemService::getItemById)
                .collect(Collectors.toList());
        return Order.builder()
                .user(user)
                .orderItems(items)
                .build();
    }

    public OrderGetDTO toDTO(Order order) {
        return OrderGetDTO.builder()
                .id(order.getId())
                .user(userMapper.toDTO(order.getUser()))
                .orderItems(order.getOrderItems().stream()
                        .map(itemMapper::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
