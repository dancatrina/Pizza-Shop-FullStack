package com.ordersystem.pizzaorder.dto.builder.PizzaBuilder;

import com.ordersystem.pizzaorder.dto.*;

import java.util.Set;

public class BuilderPizzaDTO implements IBuilderPizzaDTO {

    private PizzaDTO pizzaDTO;

    public BuilderPizzaDTO() {
        pizzaDTO = new PizzaDTO();
    }

    public BuilderPizzaDTO setIdPizza(Long idPizza) {
        this.pizzaDTO.setIdPizza(idPizza);
        return this;
    }

    public BuilderPizzaDTO setDenumirePizza(String denumirePizza) {
        this.pizzaDTO.setDenumirePizza(denumirePizza);
        return this;
    }

    public BuilderPizzaDTO setPretPizza(Double pretPizza) {
        this.pizzaDTO.setPretPizza(pretPizza);
        return this;
    }

    public BuilderPizzaDTO setTopingDTOS(Set<TopingDTO> topingDTOS) {
        this.pizzaDTO.setTopingDTOS(topingDTOS);
        return this;
    }

    public BuilderPizzaDTO setIngredientDTOS(Set<IngredientDTO> ingredientDTOS) {
        this.pizzaDTO.setIngredientDTOS(ingredientDTOS);
        return this;
    }

    public BuilderPizzaDTO setDimensiuneDTOS(Set<DimensiuneDTO> dimensiuneDTOS) {
        this.pizzaDTO.setDimensiuneDTOS(dimensiuneDTOS);
        return this;
    }

    public BuilderPizzaDTO setTipBlatDTOS(Set<TipBlatDTO> tipBlatDTOS) {
        this.pizzaDTO.setTipBlatDTOS(tipBlatDTOS);
        return this;
    }

    public BuilderPizzaDTO setSosBlatDTOS(Set<SosBlatDTO> sosBlatDTOS) {
        this.pizzaDTO.setSosBlatDTOS(sosBlatDTOS);
        return this;
    }

    public BuilderPizzaDTO setUrlPizza(String urlPizza) {
        this.pizzaDTO.setUrlPizza(urlPizza);
        return this;
    }


    @Override
    public PizzaDTO build() {
        return pizzaDTO;
    }
}
