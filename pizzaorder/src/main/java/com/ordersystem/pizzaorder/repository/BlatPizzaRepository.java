package com.ordersystem.pizzaorder.repository;

import com.ordersystem.pizzaorder.entity.TipuriBlatPizza;
import com.ordersystem.pizzaorder.repository.costum.CostumBlatPizzaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BlatPizzaRepository extends JpaRepository<TipuriBlatPizza,Long> , CostumBlatPizzaRepository {

    @Query("from tipuri_blat tb where tb.idTipBlat in :ids")
    Set<TipuriBlatPizza> getAllTipuriBlatByParamList(@Param("ids") Long... ids);

}
