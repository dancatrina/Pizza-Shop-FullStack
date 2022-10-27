package com.ordersystem.pizzaorder.service;

import com.ordersystem.pizzaorder.dto.*;
import com.ordersystem.pizzaorder.dto.wrappercollectionDTO.CollectionSetWrapper;

import java.util.Set;

public interface PizzaService {

    Set<PizzaDTO> getAllPizzas();

    PizzaDTO getPizzaById(Long id);

    PizzaDTO getPizzaFullPizza(Long id);

    void savePizza(PizzaDTO pizzaDTO);

    //Editare pizza by ID fields that are not in relation

    void deletePizzaById(Long id);

    void adaugaToppinguriByPizzaId(Long id, CollectionSetWrapper<TopingDTO> topingDTOSet);

    void adaugareIngredienteByPizzaId(Long id, CollectionSetWrapper<IngredientDTO> ingredienteDTOSet);

    void adaugareDimensinueByPizzaId(Long id, CollectionSetWrapper<DimensiuneDTO> dimensiuneDTOSet);

    void adaugareTipuriBlatByPizzaId(Long id, CollectionSetWrapper<TipBlatDTO> tipuriBlatDTOSet);

    void adaugareSosuriByPizzaId(Long id, CollectionSetWrapper<SosBlatDTO> sosBlatDTOSet);

    void deleteToppinguriByPizzaId(Long id, CollectionSetWrapper<TopingDTO> topingDTOSet);

    void deleteIngredienteByPizzaId(Long id, CollectionSetWrapper<IngredientDTO> ingredienteDTOSet);

    void deleteDimensinueByPizzaId(Long id, CollectionSetWrapper<DimensiuneDTO> dimensiuneDTOSet);

    void deleteTipuriBlatByPizzaId(Long id, CollectionSetWrapper<TipBlatDTO> tipuriBlatDTOSet);

    void deleteSosuriByPizzaId(Long id, CollectionSetWrapper<SosBlatDTO> sosBlatDTOSet);
}
