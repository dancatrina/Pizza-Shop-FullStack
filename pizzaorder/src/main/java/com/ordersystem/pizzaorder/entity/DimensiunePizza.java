package com.ordersystem.pizzaorder.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name ="dimensiuni_pizza")
public class DimensiunePizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dimensiune")
    private Long idDimensiune;

    @Column(name = "dimensiune_cm")
    private String dimensiunePizza;

    @Column(name = "pret")
    private Double pretDimensiune;

    @ManyToMany(mappedBy = "dimensiunePizzas",fetch = FetchType.LAZY,
                cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Set<Pizza> pizzaSet;

    public DimensiunePizza() {
    }

    public Long getIdDimensiune() {return idDimensiune;}
    public String getDimensiunePizza() {return dimensiunePizza;}
    public Double getPretDimensiune() {return pretDimensiune;}
    public Set<Pizza> getPizzaSet() {return pizzaSet;}

    public void setIdDimensiune(Long idDimensiune) {this.idDimensiune = idDimensiune;}
    public void setDimensiunePizza(String dimensiunePizza) {this.dimensiunePizza = dimensiunePizza;}
    public void setPretDimensiune(Double pretDimensiune) {this.pretDimensiune = pretDimensiune;}
    public void setPizzaSet(Set<Pizza> pizzaSet) {this.pizzaSet = pizzaSet;}


}
