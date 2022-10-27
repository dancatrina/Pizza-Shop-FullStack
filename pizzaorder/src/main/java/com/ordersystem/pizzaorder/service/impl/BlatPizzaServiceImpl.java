package com.ordersystem.pizzaorder.service.impl;

import com.ordersystem.pizzaorder.dto.TipBlatDTO;
import com.ordersystem.pizzaorder.entity.TipuriBlatPizza;
import com.ordersystem.pizzaorder.exception.generic.InvalidParameter;
import com.ordersystem.pizzaorder.exception.generic.NotFoundEntity;
import com.ordersystem.pizzaorder.repository.BlatPizzaRepository;
import com.ordersystem.pizzaorder.service.BlatPizzaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlatPizzaServiceImpl implements BlatPizzaService {

    private final BlatPizzaRepository blatPizzaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BlatPizzaServiceImpl(BlatPizzaRepository blatPizzaRepository, ModelMapper modelMapper) {
        this.blatPizzaRepository = blatPizzaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<TipBlatDTO> getAllBlat() {
        List<TipuriBlatPizza> tipuriBlatPizzas = blatPizzaRepository.findAll();
        return tipuriBlatPizzas.stream()
                .map(i -> modelMapper.map(i, TipBlatDTO.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public TipBlatDTO getBlatById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }
        Optional<TipuriBlatPizza> blatPizza = blatPizzaRepository.findById(id);
        if (blatPizza.isEmpty()) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        }
        return modelMapper.map(blatPizza.get(), TipBlatDTO.class);
    }

    @Override
    public void save(TipBlatDTO tipBlatDTO) {
        TipuriBlatPizza tipuriBlatPizza = modelMapper.typeMap(TipBlatDTO.class, TipuriBlatPizza.class)
                .addMappings(s -> s.skip(TipuriBlatPizza::setIdTipBlat))
                .map(tipBlatDTO);

        blatPizzaRepository.save(tipuriBlatPizza);
    }

    @Override
    public void updateTipBlatById(Long id, TipBlatDTO tipBlatDTO) {

        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }

        boolean isPresent = blatPizzaRepository.existsById(id);
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        }

        TipuriBlatPizza tipuriBlatPizza = modelMapper.map(tipBlatDTO, TipuriBlatPizza.class);
        tipuriBlatPizza.setIdTipBlat(id);

        blatPizzaRepository.save(tipuriBlatPizza);
    }

    @Override
    public void updateBlat(TipBlatDTO tipBlatDTO) {
        if (tipBlatDTO.getIdTipBlat() == null) {
            throw new InvalidParameter("ID shouldn't be " + tipBlatDTO.getIdTipBlat());
        } else if (tipBlatDTO.getIdTipBlat() < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + tipBlatDTO.getIdTipBlat() + "]");
        }
        boolean isPresent = blatPizzaRepository.existsById(tipBlatDTO.getIdTipBlat());
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + tipBlatDTO.getIdTipBlat() + " ] dosen't exist in current context");
        }

        TipuriBlatPizza tipuriBlatPizza = modelMapper.map(tipBlatDTO, TipuriBlatPizza.class);
        blatPizzaRepository.save(tipuriBlatPizza);
    }

    @Override
    public void deleteById(Long id) {
        if (id < 0) {
            throw new InvalidParameter("ID should be in [0,infinity]; CURRENT:[" + id + "]");
        }
        boolean isPresent = blatPizzaRepository.existsById(id);
        if (!isPresent) {
            throw new NotFoundEntity("ID:[ " + id + " ] dosen't exist in current context");
        } else {
            blatPizzaRepository.deletePerformanceById(id);
        }

    }
}
