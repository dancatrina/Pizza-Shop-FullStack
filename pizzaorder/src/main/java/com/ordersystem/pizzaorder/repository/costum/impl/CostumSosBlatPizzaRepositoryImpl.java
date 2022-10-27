package com.ordersystem.pizzaorder.repository.costum.impl;

import com.ordersystem.pizzaorder.repository.costum.CostumSosBlatPizzaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CostumSosBlatPizzaRepositoryImpl implements CostumSosBlatPizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void performanceDeleteById(Long id) {
        entityManager.createNativeQuery("DELETE from pizzas_sosuriblaturi_relation where pizzas_sosuriblaturi_relation.id_sos =:id ")
                .setParameter("id",id)
                .executeUpdate();

        entityManager.createQuery("DELETE FROM sosuri_blaturi where idSos = :id")
                .setParameter("id",id)
                .executeUpdate();
    }
}
