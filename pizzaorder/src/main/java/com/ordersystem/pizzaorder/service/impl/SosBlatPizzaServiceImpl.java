package com.ordersystem.pizzaorder.service.impl;

import com.ordersystem.pizzaorder.dto.SosBlatDTO;
import com.ordersystem.pizzaorder.dto.TopingDTO;
import com.ordersystem.pizzaorder.entity.SosBlatPizza;
import com.ordersystem.pizzaorder.entity.TopinguriPizza;
import com.ordersystem.pizzaorder.exception.generic.InvalidParameter;
import com.ordersystem.pizzaorder.exception.generic.NotFoundEntity;
import com.ordersystem.pizzaorder.repository.SosuriBlatPizzaRepository;
import com.ordersystem.pizzaorder.service.SosBlatPizzaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SosBlatPizzaServiceImpl implements SosBlatPizzaService {

    private ModelMapper modelMapper;
    private SosuriBlatPizzaRepository sosuriBlatPizzaRepository;

    @Autowired
    public SosBlatPizzaServiceImpl(ModelMapper modelMapper, SosuriBlatPizzaRepository sosuriBlatPizzaRepository) {
        this.modelMapper = modelMapper;
        this.sosuriBlatPizzaRepository = sosuriBlatPizzaRepository;
    }

    @Override
    public Set<SosBlatDTO> getAllSosuriBlat() {
        List<SosBlatPizza> sosBlatPizzas = sosuriBlatPizzaRepository.findAll();
        return sosBlatPizzas.stream()
                .map(i -> modelMapper.map(i, SosBlatDTO.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public SosBlatDTO getSosBlatById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }
        Optional<SosBlatPizza> sosBlatPizza = sosuriBlatPizzaRepository.findById(id);
        if (sosBlatPizza.isEmpty()) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        }
        return modelMapper.map(sosBlatPizza.get(), SosBlatDTO.class);
    }

    @Override
    public void save(SosBlatDTO sosBlatDTO) {
        var sosBlatPizza = modelMapper.typeMap(SosBlatDTO.class, SosBlatPizza.class)
                .addMappings(s -> s.skip(SosBlatPizza::setIdSos))
                .map(sosBlatDTO);

        sosuriBlatPizzaRepository.save(sosBlatPizza);
    }

    @Override
    public void updateSosBlatById(Long id, SosBlatDTO sosBlatDTO) {
        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }

        boolean isPresent = sosuriBlatPizzaRepository.existsById(id);
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        }

        SosBlatPizza sosBlatPizza = modelMapper.map(sosBlatDTO, SosBlatPizza.class);
        sosBlatPizza.setIdSos(id);

        sosuriBlatPizzaRepository.save(sosBlatPizza);
    }

    @Override
    public void updateSosBlat(SosBlatDTO sosBlatDTO) {
        if (sosBlatDTO.getIdSos() == null) {
            throw new InvalidParameter("ID shouldn't be " + sosBlatDTO.getIdSos());
        } else if (sosBlatDTO.getIdSos() < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + sosBlatDTO.getIdSos() + "]");
        }
        boolean isPresent = sosuriBlatPizzaRepository.existsById(sosBlatDTO.getIdSos());
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + sosBlatDTO.getIdSos() + " ] dosen't exist in current context");
        }
        SosBlatPizza sosBlatPizza = modelMapper.map(sosBlatDTO,SosBlatPizza.class);
        sosuriBlatPizzaRepository.save(sosBlatPizza);

    }

    @Override
    public void deleteById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }
        boolean isPresent = sosuriBlatPizzaRepository.existsById(id);
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        } else {
            sosuriBlatPizzaRepository.performanceDeleteById(id);
        }

    }
}
