package com.ordersystem.pizzaorder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SosBlatDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private Long idSos;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("denumire")
    private String denumireSos;

    public SosBlatDTO(Long idSos, String denumireSos) {this.idSos = idSos;this.denumireSos = denumireSos;}
    public SosBlatDTO() {}

    public void setIdSos(Long idSos) {this.idSos = idSos;}
    public void setDenumireSos(String denumireSos) {this.denumireSos = denumireSos;}

    public Long getIdSos() {return idSos;}
    public String getDenumireSos() {return denumireSos;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SosBlatDTO)) return false;
        SosBlatDTO that = (SosBlatDTO) o;
        return Objects.equals(idSos, that.idSos) && Objects.equals(denumireSos, that.denumireSos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSos, denumireSos);
    }
}
