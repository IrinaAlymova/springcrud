package com.nerdysoft.springcrud.repository;

import com.nerdysoft.springcrud.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select i.name, count(i.name) from items i group by i.name", nativeQuery = true)
    List<Object[]> getAllItemsCount();
}
