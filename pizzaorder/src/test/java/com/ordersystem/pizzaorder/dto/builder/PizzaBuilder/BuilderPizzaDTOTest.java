package com.ordersystem.pizzaorder.dto.builder.PizzaBuilder;


import com.ordersystem.pizzaorder.dto.DimensiuneDTO;
import com.ordersystem.pizzaorder.dto.PizzaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuilderPizzaDTOTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void checkFields(){
        PizzaDTO builderPizzaDTO = new BuilderPizzaDTO()
                .setIdPizza(1L)
                .setDenumirePizza("Pizza Angelica")
                .setPretPizza(40.0).build();

        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setIdPizza(1L);
        pizzaDTO.setDenumirePizza("Pizza Angelica");
        pizzaDTO.setPretPizza(40.0);

        assertEquals(builderPizzaDTO.getDenumirePizza(),pizzaDTO.getDenumirePizza());
        assertEquals(builderPizzaDTO.getIdPizza(),pizzaDTO.getIdPizza());
        assertEquals(builderPizzaDTO.getPretPizza(),pizzaDTO.getPretPizza());

        assertEquals(builderPizzaDTO.getDimensiuneDTOS(),pizzaDTO.getDimensiuneDTOS());
        assertEquals(builderPizzaDTO.getSosBlatDTOS(),pizzaDTO.getSosBlatDTOS());
        assertEquals(builderPizzaDTO.getIngredientDTOS(),pizzaDTO.getIngredientDTOS());
        assertEquals(builderPizzaDTO.getTipBlatDTOS(),pizzaDTO.getTipBlatDTOS());
        assertEquals(builderPizzaDTO.getTopingDTOS(),pizzaDTO.getTopingDTOS());
    }
}