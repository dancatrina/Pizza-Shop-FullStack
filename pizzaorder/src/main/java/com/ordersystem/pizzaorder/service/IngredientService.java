package com.ordersystem.pizzaorder.service;

import com.ordersystem.pizzaorder.dto.IngredientDTO;
import com.ordersystem.pizzaorder.dto.TopingDTO;

import java.util.Set;

public interface IngredientService {

    Set<IngredientDTO> getAllIngredients();

    IngredientDTO getIngredientById(Long id);

    void save(IngredientDTO ingredientDTO);

    void updateIngredientById(Long id, IngredientDTO ingredientDTO);

    void updateIngredient(IngredientDTO ingredientDTO);

    void deleteById(Long id);

}
