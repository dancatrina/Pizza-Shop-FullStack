package com.ordersystem.pizzaorder.repository;

import com.ordersystem.pizzaorder.entity.SosBlatPizza;
import com.ordersystem.pizzaorder.entity.TipuriBlatPizza;
import com.ordersystem.pizzaorder.repository.costum.CostumSosBlatPizzaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SosuriBlatPizzaRepository extends JpaRepository<SosBlatPizza,Long> , CostumSosBlatPizzaRepository {

    @Query("from sosuri_blaturi sb where sb.idSos in :ids")
    Set<SosBlatPizza> getAllSosBlaturiByParamList(@Param("ids") Long ... ids);

}
