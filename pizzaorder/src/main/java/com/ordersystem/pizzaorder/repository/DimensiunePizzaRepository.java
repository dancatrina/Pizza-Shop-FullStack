package com.ordersystem.pizzaorder.repository;

import com.ordersystem.pizzaorder.entity.DimensiunePizza;
import com.ordersystem.pizzaorder.repository.costum.CostumDimensiunePizzaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface DimensiunePizzaRepository extends JpaRepository<DimensiunePizza,Long> , CostumDimensiunePizzaRepository {


    @Query("from dimensiuni_pizza di where di.idDimensiune in :ids")
    Set<DimensiunePizza> getAllDimensiuniByParamList(@Param("ids") Long ... ids);

}
