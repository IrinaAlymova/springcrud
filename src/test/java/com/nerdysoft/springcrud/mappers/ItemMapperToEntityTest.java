package com.nerdysoft.springcrud.mappers;

import com.nerdysoft.springcrud.dto.ItemPostDTO;
import com.nerdysoft.springcrud.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ItemMapperToEntityTest {

    private final ItemMapper itemMapper = new ItemMapper();

    private ItemPostDTO itemPostDTO;
    private Item expected;

    public ItemMapperToEntityTest(ItemPostDTO itemPostDTO, Item expected) {
        this.itemPostDTO = itemPostDTO;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {ItemPostDTO.builder().name("jacket").price(new BigDecimal("20.00")).build(),
                    Item.builder().name("jacket").price(new BigDecimal("20.00")).build()},
                {ItemPostDTO.builder().name("coat").price(new BigDecimal("200.00")).build(),
                        Item.builder().name("coat").price(new BigDecimal("200.00")).build()},
                {ItemPostDTO.builder().name("gift").price(BigDecimal.ZERO).build(),
                        Item.builder().name("gift").price(BigDecimal.ZERO).build()}
        });
    }

    @Test
    public void testToEntityNameSaving() {
        assertEquals(itemMapper.toEntity(itemPostDTO).getName(), expected.getName());
    }

    @Test
    public void testToEntityPriceSaving() {
        assertEquals(itemMapper.toEntity(itemPostDTO).getPrice(), expected.getPrice());
    }
}