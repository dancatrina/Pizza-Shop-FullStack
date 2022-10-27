package com.ordersystem.pizzaorder.rest;

import com.ordersystem.pizzaorder.dto.IngredientDTO;
import com.ordersystem.pizzaorder.dto.TopingDTO;
import com.ordersystem.pizzaorder.repository.IngredientRepository;
import com.ordersystem.pizzaorder.service.IngredientService;
import com.ordersystem.pizzaorder.service.TopingPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestIngredient {

    private final IngredientService ingredientService;

    @Autowired
    public RestIngredient(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public ResponseEntity<Set<IngredientDTO>> getAllIngredients() {
        return new ResponseEntity<>(ingredientService.getAllIngredients(), HttpStatus.OK);
    }

    @GetMapping("/ingredient/{id}")
    public ResponseEntity<IngredientDTO> getTopingById(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientService.getIngredientById(id), HttpStatus.OK);
    }

    @DeleteMapping("/ingredient/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/ingredient")
    public ResponseEntity<HttpStatus> saveToping(@RequestBody IngredientDTO ingredientDTO){
        ingredientService.save(ingredientDTO);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/ingredient/{id}")
    public ResponseEntity<HttpStatus> updateTopingById(@PathVariable Long id, @RequestBody IngredientDTO ingredientDTO){
        ingredientService.updateIngredientById(id,ingredientDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/ingredient")
    public ResponseEntity<HttpStatus> updateTopingByBody(@RequestBody IngredientDTO ingredientDTO){
        ingredientService.updateIngredient(ingredientDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
