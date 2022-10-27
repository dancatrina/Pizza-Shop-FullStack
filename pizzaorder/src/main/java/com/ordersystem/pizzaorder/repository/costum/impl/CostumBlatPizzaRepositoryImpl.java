package com.ordersystem.pizzaorder.repository.costum.impl;

import com.ordersystem.pizzaorder.repository.costum.CostumBlatPizzaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CostumBlatPizzaRepositoryImpl implements CostumBlatPizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deletePerformanceById(Long id) {
        entityManager.createNativeQuery("DELETE from pizzas_tipuriblat_relation where pizzas_tipuriblat_relation.id_blat =:id ")
                .setParameter("id",id)
                .executeUpdate();

        entityManager.createQuery("DELETE FROM tipuri_blat where idTipBlat = :id")
                .setParameter("id",id)
                .executeUpdate();
    }
}
