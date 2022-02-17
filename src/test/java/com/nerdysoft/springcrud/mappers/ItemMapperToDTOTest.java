package com.nerdysoft.springcrud.mappers;

import com.nerdysoft.springcrud.dto.ItemGetDTO;
import com.nerdysoft.springcrud.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ItemMapperToDTOTest {

    private final ItemMapper itemMapper = new ItemMapper();

    private Item item;
    private ItemGetDTO expected;

    public ItemMapperToDTOTest(Item item, ItemGetDTO expected) {
        this.item = item;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {Item.builder().id(1L).name("jacket").price(new BigDecimal("20.00")).build(),
                        ItemGetDTO.builder().name("jacket").price(new BigDecimal("20.00")).build()},
                {Item.builder().id(1L).name("coat").price(new BigDecimal("200.00")).build(),
                        ItemGetDTO.builder().name("coat").price(new BigDecimal("200.00")).build()},
                {Item.builder().id(1L).name("gift").price(BigDecimal.ZERO).build(),
                        ItemGetDTO.builder().name("gift").price(BigDecimal.ZERO).build()}
        });
    }

    @Test
    public void testToDTONameSaving() {
        assertEquals(itemMapper.toDTO(item).getName(), expected.getName());
    }

    @Test
    public void testToDTOPriceSaving() {
        assertEquals(itemMapper.toDTO(item).getPrice(), expected.getPrice());
    }
}