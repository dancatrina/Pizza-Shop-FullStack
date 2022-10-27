package com.ordersystem.pizzaorder.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

public class PizzaDTO{

    @JsonProperty("id")
    private Long idPizza;

    @JsonProperty("denumire")
    private String denumirePizza;

    @JsonProperty("pret")
    private Double pretPizza;

    @JsonProperty("url")
    private String urlPizza;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("topinguri")
    private Set<TopingDTO> topingDTOS;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Ingrediente")
    private Set<IngredientDTO> ingredientDTOS;


    @JsonProperty("dimensiuni")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<DimensiuneDTO> dimensiuneDTOS;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("tipBlat")
    private Set<TipBlatDTO> tipBlatDTOS;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sos")
    private Set<SosBlatDTO> sosBlatDTOS;

    public PizzaDTO() {
    }


    public Long getIdPizza() {return idPizza;}
    public String getDenumirePizza() {return denumirePizza;}
    public Double getPretPizza() {return pretPizza;}
    public Set<TopingDTO> getTopingDTOS() {return topingDTOS;}
    public Set<IngredientDTO> getIngredientDTOS() {return ingredientDTOS;}
    public Set<DimensiuneDTO> getDimensiuneDTOS() {return dimensiuneDTOS;}
    public Set<TipBlatDTO> getTipBlatDTOS() {return tipBlatDTOS;}
    public Set<SosBlatDTO> getSosBlatDTOS() {return sosBlatDTOS;}
    public String getUrlPizza() {return urlPizza;}

    public void setIdPizza(Long idPizza) {this.idPizza = idPizza;}
    public void setDenumirePizza(String denumirePizza) {this.denumirePizza = denumirePizza;}
    public void setPretPizza(Double pretPizza) {this.pretPizza = pretPizza;}
    public void setTopingDTOS(Set<TopingDTO> topingDTOS) {this.topingDTOS = topingDTOS;}
    public void setIngredientDTOS(Set<IngredientDTO> ingredientDTOS) {this.ingredientDTOS = ingredientDTOS;}
    public void setDimensiuneDTOS(Set<DimensiuneDTO> dimensiuneDTOS) {this.dimensiuneDTOS = dimensiuneDTOS;}
    public void setTipBlatDTOS(Set<TipBlatDTO> tipBlatDTOS) {this.tipBlatDTOS = tipBlatDTOS;}
    public void setSosBlatDTOS(Set<SosBlatDTO> sosBlatDTOS) {this.sosBlatDTOS = sosBlatDTOS;}
    public void setUrlPizza(String urlPizza) {this.urlPizza = urlPizza;}

    @Override
    public String toString() {
        return "PizzaDTO{" +
                "idPizza=" + idPizza +
                ", denumirePizza='" + denumirePizza + '\'' +
                ", pretPizza=" + pretPizza +
                ", topingDTOS=" + topingDTOS +
                ", ingredientDTOS=" + ingredientDTOS +
                ", dimensiuneDTOS=" + dimensiuneDTOS +
                ", tipBlatDTOS=" + tipBlatDTOS +
                ", sosBlatDTOS=" + sosBlatDTOS +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaDTO)) return false;
        PizzaDTO pizzaDTO = (PizzaDTO) o;
        return Objects.equals(idPizza, pizzaDTO.idPizza) &&
                Objects.equals(denumirePizza, pizzaDTO.denumirePizza) &&
                Objects.equals(pretPizza, pizzaDTO.pretPizza) &&
                Objects.equals(topingDTOS, pizzaDTO.topingDTOS) &&
                Objects.equals(ingredientDTOS, pizzaDTO.ingredientDTOS) &&
                Objects.equals(dimensiuneDTOS, pizzaDTO.dimensiuneDTOS) &&
                Objects.equals(tipBlatDTOS, pizzaDTO.tipBlatDTOS) &&
                Objects.equals(sosBlatDTOS, pizzaDTO.sosBlatDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPizza, denumirePizza, pretPizza, topingDTOS, ingredientDTOS, dimensiuneDTOS, tipBlatDTOS, sosBlatDTOS);
    }
}
