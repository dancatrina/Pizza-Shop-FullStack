package com.ordersystem.pizzaorder.entity;

import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "ingrediente_pizza")
public class IngredientePizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingredient")
    private Long idIngredient;

    @Column(name = "denumire_ingredient")
    private String denumireIngredient;

    @ManyToMany(fetch = FetchType.LAZY,
        mappedBy = "ingredientePizzas")
    private Set<Pizza> pizzas;

    public Long getIdIngredient() {return idIngredient;}
    public String getDenumireIngredient() {return denumireIngredient;}
    public Set<Pizza> getPizzas() {return pizzas;}

    public void setIdIngredient(Long idIngredient) {this.idIngredient = idIngredient;}
    public void setDenumireIngredient(String denumireIngredient) {this.denumireIngredient = denumireIngredient;}
    public void setPizzas(Set<Pizza> pizzas) {this.pizzas = pizzas;}
}
