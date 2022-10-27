package com.ordersystem.pizzaorder.service.impl;

import com.ordersystem.pizzaorder.dto.PizzaDTO;
import com.ordersystem.pizzaorder.dto.builder.PizzaBuilder.BuilderPizzaDTO;
import com.ordersystem.pizzaorder.entity.Pizza;
import com.ordersystem.pizzaorder.exception.generic.InvalidParameter;
import com.ordersystem.pizzaorder.exception.generic.NotFoundEntity;
import com.ordersystem.pizzaorder.repository.*;
import com.ordersystem.pizzaorder.service.PizzaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class PizzaServiceImplTest {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllPizzas() {
        Set<PizzaDTO> actualPizza = pizzaRepository.findAll().stream().map(
                        i -> new BuilderPizzaDTO()
                                .setIdPizza(i.getIdPizza())
                                .setDenumirePizza(i.getNumePizza())
                                .setPretPizza(i.getPretPizza())
                                .build())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<PizzaDTO> pizzas = pizzaService.getAllPizzas();


        // Nu ne punem problema corectitudinii datelor deoarece acestea au fost extrase
        // din accelasi loc
        assertEquals(actualPizza.size(), pizzas.size());
    }

    @Test
    void getPizzaByIdValid() {
        Long idCautat = 1L;

        PizzaDTO pizzaActual = pizzaRepository.findById(idCautat).map(i -> new BuilderPizzaDTO()
                .setIdPizza(i.getIdPizza())
                .setDenumirePizza(i.getNumePizza())
                .setPretPizza(i.getPretPizza())
                .build()).orElseThrow(() -> new NotFoundEntity("Not found"));

        PizzaDTO expected = pizzaService.getPizzaById(idCautat);

        assertEquals(pizzaActual.getIdPizza(), expected.getIdPizza());
        assertEquals(pizzaActual.getDenumirePizza(), expected.getDenumirePizza());
        assertEquals(pizzaActual.getPretPizza(), expected.getPretPizza());
    }

    @Test
    void getPizzaByIdExceptionInvalidId() {
        Long idCautat = -1L;

        Assertions.assertThrows(InvalidParameter.class, () -> {
            pizzaService.getPizzaById(idCautat);
        });
    }

    @Test
    void getPizzaByIdExceptionNotFound() {
        Long idCautat = 9999L;

        Assertions.assertThrows(NotFoundEntity.class, () -> {
            pizzaService.getPizzaById(idCautat);
        });
    }


    //Add More;

}