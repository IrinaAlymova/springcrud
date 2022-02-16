package com.nerdysoft.springcrud.controller;

import com.nerdysoft.springcrud.entity.Item;
import com.nerdysoft.springcrud.entity.User;
import com.nerdysoft.springcrud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public void addNewItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    @GetMapping("/count")
    public List<Object[]> getAllItemsCount() {
        return itemService.getAllItemsCount();
    }

}
