package com.ordersystem.pizzaorder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class IngredientDTO {

    @JsonProperty("id")
    private Long idIngredient;

    @JsonProperty("denumire")
    private String denumireIngredient;


    public IngredientDTO(Long idIngredient, String denumireIngredient) {
        this.idIngredient = idIngredient;
        this.denumireIngredient = denumireIngredient;
    }

    public IngredientDTO() {
    }

    public Long getIdIngredient() {return idIngredient;}
    public String getDenumireIngredient() {return denumireIngredient;}

    public void setIdIngredient(Long idIngredient) {this.idIngredient = idIngredient;}
    public void setDenumireIngredient(String denumireIngredient) {this.denumireIngredient = denumireIngredient;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientDTO)) return false;
        IngredientDTO that = (IngredientDTO) o;
        return Objects.equals(idIngredient, that.idIngredient) && Objects.equals(denumireIngredient, that.denumireIngredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIngredient, denumireIngredient);
    }
}
