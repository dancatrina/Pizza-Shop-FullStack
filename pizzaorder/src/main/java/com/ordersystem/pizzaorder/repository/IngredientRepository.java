package com.ordersystem.pizzaorder.repository;

import com.ordersystem.pizzaorder.entity.DimensiunePizza;
import com.ordersystem.pizzaorder.entity.IngredientePizza;
import com.ordersystem.pizzaorder.repository.costum.CostumIngredientPizzaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface IngredientRepository extends JpaRepository<IngredientePizza,Long> , CostumIngredientPizzaRepository {

    @Query("from ingrediente_pizza ip where ip.idIngredient in :ids")
    Set<IngredientePizza> getAllIngredienteByParamList(@Param("ids") Long ... ids);

}
