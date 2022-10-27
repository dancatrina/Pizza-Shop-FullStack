package com.ordersystem.pizzaorder.service.impl;

import com.ordersystem.pizzaorder.dto.TipBlatDTO;
import com.ordersystem.pizzaorder.entity.TipuriBlatPizza;
import com.ordersystem.pizzaorder.repository.BlatPizzaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BlatPizzaServiceImplTest {

    @Mock
    private BlatPizzaRepository blatPizzaRepository;

    @Autowired
    @InjectMocks
    private BlatPizzaServiceImpl blatPizzaService;

    @Autowired
    private ModelMapper modelMapper;

    private List<TipuriBlatPizza> listTipuri;

    @BeforeEach
    public void setUp() {
        listTipuri = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        listTipuri = null;
    }

    @Test
    public void listOfAllBlats(){
        Mockito.when(blatPizzaRepository.findAll()).thenReturn(listTipuri);

        List<TipuriBlatPizza> tipuriBlatPizzasList = blatPizzaService.getAllBlat()
                .stream()
                .map( i-> modelMapper.map(i,TipuriBlatPizza.class))
                .collect(Collectors.toCollection(ArrayList::new));

        assertEquals(listTipuri,tipuriBlatPizzasList);
        Mockito.verify(blatPizzaRepository,Mockito.times(1)).findAll();
    }


}