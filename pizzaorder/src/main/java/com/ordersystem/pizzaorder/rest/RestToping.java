package com.ordersystem.pizzaorder.rest;

import com.ordersystem.pizzaorder.dto.TopingDTO;
import com.ordersystem.pizzaorder.service.TopingPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestToping {

    private TopingPizzaService topingPizzaService;

    @Autowired
    public RestToping(TopingPizzaService topingPizzaService) {
        this.topingPizzaService = topingPizzaService;
    }

    @GetMapping("/topings")
    public ResponseEntity<Set<TopingDTO>> getAllTopings() {
        return new ResponseEntity<>(topingPizzaService.getAllTopings(), HttpStatus.OK);
    }

    @GetMapping("/toping/{id}")
    public ResponseEntity<TopingDTO> getTopingById(@PathVariable Long id) {
        return new ResponseEntity<>(topingPizzaService.getTopingById(id), HttpStatus.OK);
    }

    @DeleteMapping("/toping/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        topingPizzaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/toping")
    public ResponseEntity<HttpStatus> saveToping(@RequestBody TopingDTO topingDTO){
        topingPizzaService.save(topingDTO);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/toping/{id}")
    public ResponseEntity<HttpStatus> updateTopingById(@PathVariable Long id, @RequestBody TopingDTO topingDTO){
        topingPizzaService.updateTopingById(id,topingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/toping")
    public ResponseEntity<HttpStatus> updateTopingByBody(@RequestBody TopingDTO topingDTO){
        topingPizzaService.updateToping(topingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
