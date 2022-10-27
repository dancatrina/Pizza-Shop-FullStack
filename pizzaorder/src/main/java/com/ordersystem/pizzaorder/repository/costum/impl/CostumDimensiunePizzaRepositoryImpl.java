package com.ordersystem.pizzaorder.repository.costum.impl;

import com.ordersystem.pizzaorder.repository.costum.CostumDimensiunePizzaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CostumDimensiunePizzaRepositoryImpl implements CostumDimensiunePizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deletePerformanceById(Long id) {
        entityManager.createNativeQuery("DELETE from pizzas_dimensiune_relation where pizzas_dimensiune_relation.id_dimensiune =:id ")
                .setParameter("id",id)
                .executeUpdate();

        entityManager.createQuery("DELETE FROM dimensiuni_pizza where idDimensiune = :id")
                .setParameter("id",id)
                .executeUpdate();
    }
}
