package com.ordersystem.pizzaorder.rest;


import com.ordersystem.pizzaorder.dto.*;
import com.ordersystem.pizzaorder.dto.wrappercollectionDTO.CollectionSetWrapper;
import com.ordersystem.pizzaorder.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestPizza {

    private final PizzaService pizzaService;

    @Autowired
    public RestPizza(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @CrossOrigin
    @GetMapping("/pizzas")
    public ResponseEntity<Set<PizzaDTO>> getAllPizzas(){
        return new ResponseEntity<>(pizzaService.getAllPizzas(), HttpStatus.OK);
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<PizzaDTO> getPizzaById(@RequestParam(required = false) Boolean showAll,@PathVariable Long id){
        if(showAll!=null && showAll){
            return new ResponseEntity<>(pizzaService.getPizzaFullPizza(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(pizzaService.getPizzaById(id),HttpStatus.OK);
    }

    @PostMapping("/pizza")
    public ResponseEntity<HttpStatus> savePizza(@RequestBody PizzaDTO pizzaDTO){
        pizzaService.savePizza(pizzaDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pizza/{id}")
    public ResponseEntity<HttpStatus> deletePizzaById(@PathVariable Long id ){
        pizzaService.deletePizzaById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pizza/{id}/toping")
    public ResponseEntity<HttpStatus> adaugaTopinguriByPizzaId
            (@PathVariable("id") Long id, @RequestBody CollectionSetWrapper<TopingDTO> topingDTOS){
        pizzaService.adaugaToppinguriByPizzaId(id,topingDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pizza/{id}/ingredient")
    public ResponseEntity<HttpStatus> adaugaIngredientByPizzaId
            (@PathVariable("id") Long id, @RequestBody CollectionSetWrapper<IngredientDTO> ingredientDTOS){
        pizzaService.adaugareIngredienteByPizzaId(id,ingredientDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pizza/{id}/dimensiune")
    public ResponseEntity<HttpStatus> adaugaDimensiuneByPizzaId
            (@PathVariable("id") Long id, @RequestBody CollectionSetWrapper<DimensiuneDTO> dimensiuneDTOS){
        pizzaService.adaugareDimensinueByPizzaId(id,dimensiuneDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pizza/{id}/tipblat")
    public ResponseEntity<HttpStatus> adaugaTipuriBlatByPizzaId
            (@PathVariable("id") Long id, @RequestBody CollectionSetWrapper<TipBlatDTO> tipBlatDTOS){
        pizzaService.adaugareTipuriBlatByPizzaId(id,tipBlatDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pizza/{id}/sos")
    public ResponseEntity<HttpStatus> adaugaSosuriBlatByPizzaId
            (@PathVariable("id") Long id, @RequestBody CollectionSetWrapper<SosBlatDTO> sosBlatDTOS){
        pizzaService.adaugareSosuriByPizzaId(id,sosBlatDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pizza/{id}/toping")
    public ResponseEntity<HttpStatus> deleteTopingPizzaById(@PathVariable Long id,
                                                            @RequestBody CollectionSetWrapper<TopingDTO> topingDTOS){
        pizzaService.deleteToppinguriByPizzaId(id,topingDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pizza/{id}/ingredient")
    public ResponseEntity<HttpStatus> deleteIngredientPizzaById(@PathVariable Long id,
                                                            @RequestBody CollectionSetWrapper<IngredientDTO> ingredientDTOS){
        pizzaService.deleteIngredienteByPizzaId(id,ingredientDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pizza/{id}/dimensiune")
    public ResponseEntity<HttpStatus> deleteDimensiunePizzaById(@PathVariable Long id,
                                                            @RequestBody CollectionSetWrapper<DimensiuneDTO> dimensiuneDTOS){
        pizzaService.deleteDimensinueByPizzaId(id,dimensiuneDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pizza/{id}/tipblat")
    public ResponseEntity<HttpStatus> deleteTipBlatPizzaById(@PathVariable Long id,
                                                            @RequestBody CollectionSetWrapper<TipBlatDTO> tipBlatDTOS){
        pizzaService.deleteTipuriBlatByPizzaId(id,tipBlatDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pizza/{id}/sos")
    public ResponseEntity<HttpStatus> deleteSosPizzaById(@PathVariable Long id,
                                                            @RequestBody CollectionSetWrapper<SosBlatDTO> sosBlatDTOS){
        pizzaService.deleteSosuriByPizzaId(id,sosBlatDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
