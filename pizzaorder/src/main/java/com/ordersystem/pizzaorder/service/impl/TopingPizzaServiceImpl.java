package com.ordersystem.pizzaorder.service.impl;

import com.ordersystem.pizzaorder.dto.TopingDTO;
import com.ordersystem.pizzaorder.entity.TopinguriPizza;
import com.ordersystem.pizzaorder.exception.generic.InvalidParameter;
import com.ordersystem.pizzaorder.exception.generic.NotFoundEntity;
import com.ordersystem.pizzaorder.repository.TopingPizzaRepository;
import com.ordersystem.pizzaorder.service.TopingPizzaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TopingPizzaServiceImpl implements TopingPizzaService {

    private ModelMapper modelMapper;
    private TopingPizzaRepository topingPizzaRepository;

    @Autowired
    public TopingPizzaServiceImpl(ModelMapper modelMapper, TopingPizzaRepository topingPizzaRepository) {
        this.modelMapper = modelMapper;
        this.topingPizzaRepository = topingPizzaRepository;
    }

    @Override
    public Set<TopingDTO> getAllTopings() {
        List<TopinguriPizza> topinguriPizzas = topingPizzaRepository.findAll();
        return topinguriPizzas.stream()
                .map(i -> modelMapper.map(i, TopingDTO.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public TopingDTO getTopingById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }
        Optional<TopinguriPizza> topinguriPizza = topingPizzaRepository.findById(id);
        if (topinguriPizza.isEmpty()) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        }
        return modelMapper.map(topinguriPizza.get(), TopingDTO.class);
    }

    @Override
    public void save(TopingDTO topingDTO) {
        var topinguriPizza = modelMapper.typeMap(TopingDTO.class, TopinguriPizza.class)
                .addMappings(s -> s.skip(TopinguriPizza::setIdToping))
                .map(topingDTO);

        topingPizzaRepository.save(topinguriPizza);
    }

    @Override
    public void updateTopingById(Long id, TopingDTO topingDTO) {
        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }

        boolean isPresent = topingPizzaRepository.existsById(id);
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        }

        TopinguriPizza topinguriPizza = modelMapper.map(topingDTO, TopinguriPizza.class);
        topinguriPizza.setIdToping(id);

        topingPizzaRepository.save(topinguriPizza);

    }

    @Override
    public void updateToping(TopingDTO topingDTO) {

        if (topingDTO.getIdToping() == null) {
            throw new InvalidParameter("ID shouldn't be " + topingDTO.getIdToping());
        } else if (topingDTO.getIdToping() < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + topingDTO.getIdToping() + "]");
        }
        boolean isPresent = topingPizzaRepository.existsById(topingDTO.getIdToping());
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + topingDTO.getIdToping() + " ] dosen't exist in current context");
        }

        TopinguriPizza topinguriPizza = modelMapper.map(topingDTO,TopinguriPizza.class);
        topingPizzaRepository.save(topinguriPizza);

    }

    @Override
    public void deleteById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }
        boolean isPresent = topingPizzaRepository.existsById(id);
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        } else {
            topingPizzaRepository.performanceDeleteById(id);
        }
    }
}
