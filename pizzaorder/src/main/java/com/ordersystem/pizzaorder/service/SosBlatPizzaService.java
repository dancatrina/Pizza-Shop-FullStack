package com.ordersystem.pizzaorder.service;

import com.ordersystem.pizzaorder.dto.SosBlatDTO;
import com.ordersystem.pizzaorder.dto.TipBlatDTO;

import java.util.Set;

public interface SosBlatPizzaService {

    Set<SosBlatDTO> getAllSosuriBlat();

    SosBlatDTO getSosBlatById(Long id);

    void save(SosBlatDTO sosBlatDTO);

    void updateSosBlatById(Long id, SosBlatDTO sosBlatDTO);

    void updateSosBlat(SosBlatDTO sosBlatDTO);

    void deleteById(Long id);
}
