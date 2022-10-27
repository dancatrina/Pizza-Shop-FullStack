package com.ordersystem.pizzaorder.repository.costum.impl;

import com.ordersystem.pizzaorder.repository.costum.CostumIngredientPizzaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CostumIngredientPizzaRepositoryImpl implements CostumIngredientPizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void performanceDeleteById(Long id) {
        entityManager.createNativeQuery("DELETE from pizzas_ingrediente_relation where pizzas_ingrediente_relation.id_ingredient =:id ")
                .setParameter("id",id)
                .executeUpdate();

        entityManager.createQuery("DELETE FROM ingrediente_pizza where idIngredient = :id")
                .setParameter("id",id)
                .executeUpdate();
    }
}
