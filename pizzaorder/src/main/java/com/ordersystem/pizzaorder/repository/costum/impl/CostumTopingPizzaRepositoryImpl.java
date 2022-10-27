package com.ordersystem.pizzaorder.repository.costum.impl;

import com.ordersystem.pizzaorder.repository.costum.CostumTopingPizzaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CostumTopingPizzaRepositoryImpl implements CostumTopingPizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void performanceDeleteById(Long id) {
        entityManager.createNativeQuery("DELETE from pizzas_topping_relation where pizzas_topping_relation.id_toping =:id ")
                .setParameter("id",id)
                .executeUpdate();

        entityManager.createQuery("DELETE FROM topinguri_pizza where idToping = :id")
                .setParameter("id",id)
                .executeUpdate();
    }
}
