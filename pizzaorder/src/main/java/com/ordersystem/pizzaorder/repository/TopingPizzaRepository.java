package com.ordersystem.pizzaorder.repository;

import com.ordersystem.pizzaorder.entity.TopinguriPizza;
import com.ordersystem.pizzaorder.repository.costum.CostumTopingPizzaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TopingPizzaRepository extends JpaRepository<TopinguriPizza,Long> , CostumTopingPizzaRepository {

    @Query("from topinguri_pizza tp where tp.idToping in :ids")
    Set<TopinguriPizza> getAllTopingsByParamList(@Param("ids") Long ... ids);

}
