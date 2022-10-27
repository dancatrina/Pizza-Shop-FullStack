package com.ordersystem.pizzaorder.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "sosuri_blaturi")
public class SosBlatPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sos")
    private Long idSos;

    @Column(name = "denumire_sos")
    private String denumireSos;

    @ManyToMany(mappedBy = "sosBlatPizzas",fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Set<Pizza> pizzas;

    public SosBlatPizza() {
    }

    public Long getIdSos() {return idSos;}
    public String getDenumireSos() {return denumireSos;}
    public Set<Pizza> getPizzas() {return pizzas;}

    public void setIdSos(Long idSos) {this.idSos = idSos;}
    public void setDenumireSos(String denumireSos) {this.denumireSos = denumireSos;}
    public void setPizzas(Set<Pizza> pizzas) {this.pizzas = pizzas;}
}
