package com.ordersystem.pizzaorder.repository;

import com.ordersystem.pizzaorder.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    @Modifying
    @Query("delete from pizzas pi where pi.idPizza = :id")
    void deletePerformanceById(@Param("id") Long id);

    @Modifying
    @Query(value = "DELETE from pizzas_topping_relation where id_pizza = :idPizza AND id_toping in :topingIds ",
            nativeQuery = true)
    void deleteAllTopingsByPizzaID(@Param("idPizza") Long idPizza, @Param("topingIds") Long ... topingIds);

    @Modifying
    @Query(value = "DELETE from pizzas_ingrediente_relation where id_pizza = :idPizza AND id_ingredient in :topingIds ",
            nativeQuery = true)
    void deleteAllIngredientsByPizzaID(@Param("idPizza") Long idPizza, @Param("topingIds") Long ... topingIds);

    @Modifying
    @Query(value = "DELETE from pizzas_dimensiune_relation where id_pizza = :idPizza AND id_dimensiune in :topingIds ",
            nativeQuery = true)
    void deleteAllDimensiuneByPizzaID(@Param("idPizza") Long idPizza, @Param("topingIds") Long ... topingIds);

    @Modifying
    @Query(value = "DELETE from pizzas_tipuriblat_relation where id_pizza = :idPizza AND id_blat in :topingIds ",
            nativeQuery = true)
    void deleteAllTipBlatByPizzaID(@Param("idPizza") Long idPizza, @Param("topingIds") Long ... topingIds);
    @Modifying
    @Query(value = "DELETE from pizzas_sosuriblaturi_relation where id_pizza = :idPizza AND id_sos in :topingIds ",
            nativeQuery = true)
    void deleteAllSosusriByPizzaID(@Param("idPizza") Long idPizza, @Param("topingIds") Long ... topingIds);
}
