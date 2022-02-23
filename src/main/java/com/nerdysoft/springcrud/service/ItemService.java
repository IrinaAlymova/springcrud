package com.nerdysoft.springcrud.service;

import com.nerdysoft.springcrud.entity.Item;
import com.nerdysoft.springcrud.exceptions.DuplicateException;
import com.nerdysoft.springcrud.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item newItem) {
        Item item = itemRepository.findByName(newItem.getName());
        if (item != null) {
            logger.info("item with name: " + newItem.getName() + " already exists");
            throw new DuplicateException("such item already exists");
        }
        return itemRepository.save(newItem);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> {
            logger.info("item with id: " + id + " not found");
            return new IllegalArgumentException("item not found");
        });
    }

    public void deleteItem(Long id) {
        Item item = getItemById(id);
        itemRepository.delete(item);
    }

    
}
