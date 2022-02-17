package com.nerdysoft.springcrud.mappers;

import com.nerdysoft.springcrud.dto.ItemGetDTO;
import com.nerdysoft.springcrud.dto.ItemPostDTO;
import com.nerdysoft.springcrud.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toEntity(ItemPostDTO itemPostDTO) {
        return Item.builder()
                .name(itemPostDTO.getName())
                .price(itemPostDTO.getPrice())
                .build();
    }

    public ItemGetDTO toDTO(Item item) {
        return ItemGetDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }
}
