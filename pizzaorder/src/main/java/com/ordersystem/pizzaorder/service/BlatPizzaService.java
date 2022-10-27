package com.ordersystem.pizzaorder.service;

import com.ordersystem.pizzaorder.dto.DimensiuneDTO;
import com.ordersystem.pizzaorder.dto.TipBlatDTO;

import java.util.Set;

public interface BlatPizzaService {
    Set<TipBlatDTO> getAllBlat();

    TipBlatDTO getBlatById(Long id);

    void save(TipBlatDTO tipBlatDTO);

    void updateTipBlatById(Long id, TipBlatDTO tipBlatDTO);

    void updateBlat(TipBlatDTO tipBlatDTO);

    void deleteById(Long id);
}
