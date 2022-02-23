package com.nerdysoft.springcrud.repository;

import com.nerdysoft.springcrud.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
}
