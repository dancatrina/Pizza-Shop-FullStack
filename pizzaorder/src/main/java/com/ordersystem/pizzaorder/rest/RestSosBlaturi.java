package com.ordersystem.pizzaorder.rest;

import com.ordersystem.pizzaorder.dto.SosBlatDTO;
import com.ordersystem.pizzaorder.service.SosBlatPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestSosBlaturi {

    private final SosBlatPizzaService sosBlatPizzaService;

    @Autowired
    public RestSosBlaturi(SosBlatPizzaService sosBlatPizzaService) {
        this.sosBlatPizzaService = sosBlatPizzaService;
    }

    @GetMapping("/sosblaturi")
    public ResponseEntity<Set<SosBlatDTO>> getAllBlaturi() {
        return new ResponseEntity<>(sosBlatPizzaService.getAllSosuriBlat(), HttpStatus.OK);
    }

    @GetMapping("/sosblat/{id}")
    public ResponseEntity<SosBlatDTO> getSosBlatById(@PathVariable Long id) {
        return new ResponseEntity<>(sosBlatPizzaService.getSosBlatById(id), HttpStatus.OK);
    }

    @DeleteMapping("/sosblat/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        sosBlatPizzaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/sosblat")
    public ResponseEntity<HttpStatus> saveSosBlat(@RequestBody SosBlatDTO sosBlatDTO) {
        sosBlatPizzaService.save(sosBlatDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/sosblat/{id}")
    public ResponseEntity<HttpStatus> updateSosBlatById(@PathVariable Long id, @RequestBody SosBlatDTO sosBlatDTO) {
        sosBlatPizzaService.updateSosBlatById(id, sosBlatDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/sosblat")
    public ResponseEntity<HttpStatus> updateTipBlatByBody(@RequestBody SosBlatDTO sosBlatDTO) {
        sosBlatPizzaService.updateSosBlat(sosBlatDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
