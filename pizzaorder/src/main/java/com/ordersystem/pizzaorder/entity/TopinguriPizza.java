package com.ordersystem.pizzaorder.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "topinguri_pizza")
public class TopinguriPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_toping")
    private Long idToping;

    @Column(name = "denumire_toping")
    private String denumiteToping;

    @Column(name = "pret_toping")
    private Double pretToping;

    @ManyToMany(mappedBy = "ingredientePizzas",fetch = FetchType.LAZY)
    private Set<Pizza> pizzas;

    public TopinguriPizza() {
    }

    public Long getIdToping() {return idToping;}
    public String getDenumiteToping() {return denumiteToping;}
    public Double getPretToping() {return pretToping;}
    public Set<Pizza> getPizzas() {return pizzas;}

    public void setIdToping(Long idToping) {this.idToping = idToping;}
    public void setDenumiteToping(String denumiteToping) {this.denumiteToping = denumiteToping;}
    public void setPretToping(Double pretToping) {this.pretToping = pretToping;}
    public void setPizzas(Set<Pizza> topinguriPizzas) {this.pizzas = topinguriPizzas;}


}
