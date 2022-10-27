package com.ordersystem.pizzaorder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TipBlatDTO {

    @JsonProperty("id")
    private Long idTipBlat;

    @JsonProperty("denumire")
    private String denumireBlat;

    public TipBlatDTO() {
    }

    public TipBlatDTO(Long idTipBlat, String denumireBlat) {
        this.idTipBlat = idTipBlat;
        this.denumireBlat = denumireBlat;
    }

    public Long getIdTipBlat() {return idTipBlat;}
    public String getDenumireBlat() {return denumireBlat;}

    public void setIdTipBlat(Long idTipBlat) {this.idTipBlat = idTipBlat;}
    public void setDenumireBlat(String denumireBlat) {this.denumireBlat = denumireBlat;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipBlatDTO)) return false;
        TipBlatDTO that = (TipBlatDTO) o;
        return Objects.equals(idTipBlat, that.idTipBlat) && Objects.equals(denumireBlat, that.denumireBlat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipBlat, denumireBlat);
    }
}
