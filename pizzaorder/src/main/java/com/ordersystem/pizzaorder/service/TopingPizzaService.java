package com.ordersystem.pizzaorder.service;

import com.ordersystem.pizzaorder.dto.TopingDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface TopingPizzaService {

    Set<TopingDTO> getAllTopings();

    TopingDTO getTopingById(Long id);

    void save(TopingDTO topingDTO);

    void updateTopingById(Long id, TopingDTO topingDTO);

    void updateToping(TopingDTO topingDTO);

    void deleteById(Long id);
}
