package com.ordersystem.pizzaorder.rest;

import com.ordersystem.pizzaorder.dto.DimensiuneDTO;
import com.ordersystem.pizzaorder.dto.IngredientDTO;
import com.ordersystem.pizzaorder.entity.DimensiunePizza;
import com.ordersystem.pizzaorder.service.DimensiunePizzaService;
import com.ordersystem.pizzaorder.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestDimensiune {

    private final DimensiunePizzaService dimensiunePizzaService;

    @Autowired
    public RestDimensiune(DimensiunePizzaService dimensiunePizzaService) {
        this.dimensiunePizzaService = dimensiunePizzaService;
    }

    @GetMapping("/dimensiuni")
    public ResponseEntity<Set<DimensiuneDTO>> getAllDimensiune() {
        return new ResponseEntity<>(dimensiunePizzaService.getAllDimensiune(), HttpStatus.OK);
    }

    @GetMapping("/dimensiune/{id}")
    public ResponseEntity<DimensiuneDTO> getDimensiuneById(@PathVariable Long id) {
        return new ResponseEntity<>(dimensiunePizzaService.getDimensiuneById(id), HttpStatus.OK);
    }

    @DeleteMapping("/dimensiune/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        dimensiunePizzaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/dimensiune")
    public ResponseEntity<HttpStatus> saveDimensiune(@RequestBody DimensiuneDTO dimensiuneDTO) {
        dimensiunePizzaService.save(dimensiuneDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/dimensiune/{id}")
    public ResponseEntity<HttpStatus> updateDimensiuneById(@PathVariable Long id, @RequestBody DimensiuneDTO dimensiuneDTO){
        dimensiunePizzaService.updateDimensiuneById(id,dimensiuneDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/dimensiune")
    public ResponseEntity<HttpStatus> updateDimensiuneByBody(@RequestBody DimensiuneDTO dimensiuneDTO){
        dimensiunePizzaService.updateDimensiune(dimensiuneDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
