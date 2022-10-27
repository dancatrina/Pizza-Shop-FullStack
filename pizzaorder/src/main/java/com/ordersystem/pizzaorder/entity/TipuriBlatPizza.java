package com.ordersystem.pizzaorder.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "tipuri_blat")
public class TipuriBlatPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_blat")
    private Long idTipBlat;

    @Column(name = "denumire_blat")
    private String denumireTipBlat;

    @ManyToMany(mappedBy = "tipuriBlatPizzas",fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Set<Pizza> pizzas;

    public TipuriBlatPizza() {
    }

    public Long getIdTipBlat() {return idTipBlat;}
    public String getDenumireTipBlat() {return denumireTipBlat;}
    public Set<Pizza> getPizzas() {return pizzas;}

    public void setIdTipBlat(Long idTipBlat) {this.idTipBlat = idTipBlat;}
    public void setDenumireTipBlat(String denumireTipBlat) {this.denumireTipBlat = denumireTipBlat;}
    public void setPizzas(Set<Pizza> pizzas) {this.pizzas = pizzas;}



}
