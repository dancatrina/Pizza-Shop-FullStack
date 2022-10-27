package com.ordersystem.pizzaorder.rest;


import com.ordersystem.pizzaorder.dto.IngredientDTO;
import com.ordersystem.pizzaorder.dto.TipBlatDTO;
import com.ordersystem.pizzaorder.service.BlatPizzaService;
import com.ordersystem.pizzaorder.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestTipuriBlat {

    private final BlatPizzaService blatPizzaService;

    @Autowired
    public RestTipuriBlat(BlatPizzaService blatPizzaService) {
        this.blatPizzaService = blatPizzaService;
    }

    @GetMapping("/tipblaturi")
    public ResponseEntity<Set<TipBlatDTO>> getAllblaturi() {
        return new ResponseEntity<>(blatPizzaService.getAllBlat(), HttpStatus.OK);
    }

    @GetMapping("/tipblat/{id}")
    public ResponseEntity<TipBlatDTO> getTipBlatById(@PathVariable Long id) {
        return new ResponseEntity<>(blatPizzaService.getBlatById(id), HttpStatus.OK);
    }

    @DeleteMapping("/tipblat/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        blatPizzaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/tipblat")
    public ResponseEntity<HttpStatus> saveBlat(@RequestBody TipBlatDTO tipBlatDTO) {
        blatPizzaService.save(tipBlatDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/tipblat/{id}")
    public ResponseEntity<HttpStatus> updateTipBlatById(@PathVariable Long id, @RequestBody TipBlatDTO tipBlatDTO) {
        blatPizzaService.updateTipBlatById(id, tipBlatDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/tipblat")
    public ResponseEntity<HttpStatus> updateTipBlatByBody(@RequestBody TipBlatDTO tipBlatDTO) {
        blatPizzaService.updateBlat(tipBlatDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
