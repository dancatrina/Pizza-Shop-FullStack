package com.ordersystem.pizzaorder.service;

import com.ordersystem.pizzaorder.dto.DimensiuneDTO;
import com.ordersystem.pizzaorder.entity.DimensiunePizza;

import java.util.Set;

public interface DimensiunePizzaService {

    Set<DimensiuneDTO> getAllDimensiune();

    DimensiuneDTO getDimensiuneById(Long id);

    void save(DimensiuneDTO dimensiuneDTO);

    void updateDimensiuneById(Long id, DimensiuneDTO dimensiuneDTO);

    void updateDimensiune(DimensiuneDTO dimensiuneDTO);

    void deleteById(Long id);
}
