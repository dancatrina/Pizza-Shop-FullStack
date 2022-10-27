package com.ordersystem.pizzaorder.service.impl;

import com.ordersystem.pizzaorder.dto.IngredientDTO;
import com.ordersystem.pizzaorder.entity.IngredientePizza;
import com.ordersystem.pizzaorder.exception.generic.InvalidParameter;
import com.ordersystem.pizzaorder.exception.generic.NotFoundEntity;
import com.ordersystem.pizzaorder.repository.IngredientRepository;
import com.ordersystem.pizzaorder.service.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository, ModelMapper modelMapper) {
        this.ingredientRepository = ingredientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<IngredientDTO> getAllIngredients() {
        return ingredientRepository.findAll()
                .stream().map(i -> modelMapper.map(i, IngredientDTO.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public IngredientDTO getIngredientById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("Id should be in : [0,infinty]; current:[" + id + "]");
        }
        Optional<IngredientePizza> ingredientePizza = ingredientRepository.findById(id);
        if (ingredientePizza.isEmpty()) {
            throw new NotFoundEntity("Coudn't found anything with requested id");
        }

        return modelMapper.map(ingredientePizza.get(), IngredientDTO.class);
    }

    @Override
    public void save(IngredientDTO ingredientDTO) {
        IngredientePizza ingredientePizza = modelMapper.typeMap(IngredientDTO.class, IngredientePizza.class)
                .addMappings(s -> s.skip(IngredientePizza::setIdIngredient))
                .map(ingredientDTO);

        ingredientRepository.save(ingredientePizza);
    }

    @Override
    public void updateIngredientById(Long id, IngredientDTO ingredientDTO) {
        if (id < 0) {
            throw new InvalidParameter("Id should be in : [0,infinty]; current:[" + id + "]");
        }
        boolean isPresent = ingredientRepository.existsById(id);
        if (!isPresent) {
            throw new NotFoundEntity("Coudn't found anything with requested id");
        }

        IngredientePizza ingredientePizza = modelMapper.map(ingredientDTO, IngredientePizza.class);
        ingredientePizza.setIdIngredient(id);

        ingredientRepository.save(ingredientePizza);

    }

    @Override
    public void updateIngredient(IngredientDTO ingredientDTO) {
        if (ingredientDTO.getIdIngredient() == null) {
            throw new InvalidParameter("ID shouldn't be " + ingredientDTO.getIdIngredient());
        } else if (ingredientDTO.getIdIngredient() < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + ingredientDTO.getIdIngredient() + "]");
        }
        boolean isPresent = ingredientRepository.existsById(ingredientDTO.getIdIngredient());
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + ingredientDTO.getIdIngredient() + " ] dosen't exist in current context");
        }

        IngredientePizza ingredientePizza = modelMapper.map(ingredientDTO, IngredientePizza.class);
        ingredientRepository.save(ingredientePizza);

    }

    @Override
    public void deleteById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("Id should be in : [0,infinty]; current:[" + id + "]");
        }
        boolean isPresent = ingredientRepository.existsById(id);
        if (!isPresent) {
            throw new NotFoundEntity("Coudn't found anything with requested id");
        }
        ingredientRepository.performanceDeleteById(id);
    }
}
