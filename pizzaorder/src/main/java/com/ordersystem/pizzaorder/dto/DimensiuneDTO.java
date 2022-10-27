package com.ordersystem.pizzaorder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DimensiuneDTO {

    @JsonProperty("id")
    private Long idDimensiune;

    @JsonProperty("dimensiune")
    private String dimensiunePizza;

    @JsonProperty("pret")
    private Double pretDimensiune;

    public DimensiuneDTO() {
    }

    public DimensiuneDTO(Long idDimensiune, String dimensiuneCm, Double pretDimensiune) {
        this.idDimensiune = idDimensiune;
        this.dimensiunePizza = dimensiuneCm;
        this.pretDimensiune = pretDimensiune;
    }

    public Long getIdDimensiune() {return idDimensiune;}
    public Double getPretDimensiune() {return pretDimensiune;}
    public String getDimensiunePizza() {return dimensiunePizza;}

    public void setIdDimensiune(Long idDimensiune) {this.idDimensiune = idDimensiune;}
    public void setPretDimensiune(Double pretDimensiune) {this.pretDimensiune = pretDimensiune;}
    public void setDimensiunePizza(String dimensiunePizza) {this.dimensiunePizza = dimensiunePizza;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DimensiuneDTO)) return false;
        DimensiuneDTO that = (DimensiuneDTO) o;
        return Objects.equals(idDimensiune, that.idDimensiune) && Objects.equals(dimensiunePizza, that.dimensiunePizza) && Objects.equals(pretDimensiune, that.pretDimensiune);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDimensiune, dimensiunePizza, pretDimensiune);
    }
}
