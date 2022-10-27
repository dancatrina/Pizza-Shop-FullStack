package com.ordersystem.pizzaorder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TopingDTO {

    @JsonProperty("id")
    private Long idToping;

    @JsonProperty("denumire")
    private String denumiteToping;

    @JsonProperty("pret")
    private Double pretToping;

    public TopingDTO(Long idToping, String denumireToping, Double pretToping) {
        this.idToping = idToping;
        this.denumiteToping = denumireToping;
        this.pretToping = pretToping;
    }

    public TopingDTO() {}

    public Long getIdToping() {return idToping;}
    public String getdenumiteToping() {return denumiteToping;}
    public Double getPretToping() {return pretToping;}

    public void setIdToping(Long idToping) {this.idToping = idToping;}
    public void setdenumiteToping(String denumireToping) {this.denumiteToping = denumireToping;}
    public void setPretToping(Double pretToping) {this.pretToping = pretToping;}


    @Override
    public String toString() {
        return "TopingDTO{" +
                "idToping=" + idToping +
                ", denumiteToping='" + denumiteToping + '\'' +
                ", pretToping=" + pretToping +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopingDTO)) return false;
        TopingDTO topingDTO = (TopingDTO) o;
        return Objects.equals(idToping, topingDTO.idToping) && Objects.equals(denumiteToping, topingDTO.denumiteToping) && Objects.equals(pretToping, topingDTO.pretToping);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idToping, denumiteToping, pretToping);
    }
}
