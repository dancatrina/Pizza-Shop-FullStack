package com.ordersystem.pizzaorder.service.impl;

import com.ordersystem.pizzaorder.dto.DimensiuneDTO;
import com.ordersystem.pizzaorder.entity.DimensiunePizza;
import com.ordersystem.pizzaorder.entity.Pizza;
import com.ordersystem.pizzaorder.exception.generic.InvalidParameter;
import com.ordersystem.pizzaorder.exception.generic.NotFoundEntity;
import com.ordersystem.pizzaorder.repository.DimensiunePizzaRepository;
import com.ordersystem.pizzaorder.service.DimensiunePizzaService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DimensiunePizzaServiceImpl implements DimensiunePizzaService {

    private final DimensiunePizzaRepository dimensiunePizzaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DimensiunePizzaServiceImpl(DimensiunePizzaRepository dimensiunePizzaRepository, ModelMapper modelMapper) {
        this.dimensiunePizzaRepository = dimensiunePizzaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<DimensiuneDTO> getAllDimensiune() {
        List<DimensiunePizza> dimensiunePizzas = dimensiunePizzaRepository.findAll();
        return dimensiunePizzas.stream()
                .map(i -> modelMapper.map(i, DimensiuneDTO.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public DimensiuneDTO getDimensiuneById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }
        Optional<DimensiunePizza> dimensiunePizza = dimensiunePizzaRepository.findById(id);
        if (dimensiunePizza.isEmpty()) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        }
        return modelMapper.map(dimensiunePizza.get(), DimensiuneDTO.class);
    }

    @Override
    public void save(DimensiuneDTO dimensiuneDTO) {
        DimensiunePizza dimensiuneSave = modelMapper.typeMap(DimensiuneDTO.class, DimensiunePizza.class)
                .addMappings(s -> s.skip(DimensiunePizza::setIdDimensiune))
                .map(dimensiuneDTO);
        dimensiunePizzaRepository.save(dimensiuneSave);
    }

    @Override
    public void updateDimensiuneById(Long id, DimensiuneDTO dimensiuneDTO) {

        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }

        boolean isPresent = dimensiunePizzaRepository.existsById(id);
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        }

        DimensiunePizza dimensiunePizza = modelMapper.map(dimensiuneDTO, DimensiunePizza.class);
        dimensiunePizza.setIdDimensiune(id);

        dimensiunePizzaRepository.save(dimensiunePizza);

    }

    @Override
    public void updateDimensiune(DimensiuneDTO dimensiuneDTO) {

        if (dimensiuneDTO.getIdDimensiune() == null) {
            throw new InvalidParameter("ID shouldn't be " + dimensiuneDTO.getIdDimensiune());
        } else if (dimensiuneDTO.getIdDimensiune() < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + dimensiuneDTO.getIdDimensiune() + "]");
        }
        boolean isPresent = dimensiunePizzaRepository.existsById(dimensiuneDTO.getIdDimensiune());
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + dimensiuneDTO.getIdDimensiune() + " ] dosen't exist in current context");
        }

        DimensiunePizza dimensiunePizza = modelMapper.map(dimensiuneDTO, DimensiunePizza.class);
        dimensiunePizzaRepository.save(dimensiunePizza);

    }

    @Override
    public void deleteById(Long id) {

        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }
        Optional<DimensiunePizza> dimensiunePizza = dimensiunePizzaRepository.findById(id);
        if (dimensiunePizza.isEmpty()) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        } else {
            Set<Pizza> pizzas = dimensiunePizza.get().getPizzaSet();
            for (Pizza el : pizzas) {
                el.getDimensiunePizzas().remove(dimensiunePizza.get());
            }
           // dimensiunePizzaRepository.deletePerformanceById(id); (FOR BIG PERFORMANCE);
            dimensiunePizzaRepository.delete(dimensiunePizza.get());
        }
    }

}

