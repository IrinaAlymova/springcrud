package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.dto.ItemGetDTO;
import com.nerdysoft.springcrud.dto.ItemPostDTO;
import com.nerdysoft.springcrud.mappers.ItemMapper;
import com.nerdysoft.springcrud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }


    @PreAuthorize("hasAuthority('create_item')")
    @PostMapping
    public ItemGetDTO addNewItem(@RequestBody ItemPostDTO itemPostDTO) {
        return itemMapper.toDTO(itemService.addItem(itemMapper.toEntity(itemPostDTO)));
    }


    @PreAuthorize("hasAuthority('view_item')")
    @GetMapping("/{id}")
    public ItemGetDTO getItemById(@PathVariable Long id) {
        return itemMapper.toDTO(itemService.getItemById(id));
    }


    @PreAuthorize("hasAuthority('view_all_items')")
    @GetMapping
    public List<ItemGetDTO> getAllItems() {
        return itemService.getAllItems().stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }


    @PreAuthorize("hasAuthority('delete_item')")
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

}
