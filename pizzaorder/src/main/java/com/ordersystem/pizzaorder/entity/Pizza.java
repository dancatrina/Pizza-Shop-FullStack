package com.ordersystem.pizzaorder.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity (name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_pizza")
    private Long idPizza;

    @Column(name = "nume_pizza")
    private String numePizza;

    @Column(name = "pret")
    private Double pretPizza;

    @Column(name = "url")
    private String urlPizza;

    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="pizzas_topping_relation",
            joinColumns = @JoinColumn(name = "id_pizza"),
            inverseJoinColumns =@JoinColumn(name = "id_toping"))
    private Set<TopinguriPizza> topinguriPizzas;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "pizzas_ingrediente_relation",
            joinColumns =@JoinColumn(name ="id_pizza"),
            inverseJoinColumns = @JoinColumn(name ="id_ingredient"))
    private Set<IngredientePizza> ingredientePizzas;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "pizzas_dimensiune_relation",
            joinColumns =@JoinColumn(name = "id_pizza"),
            inverseJoinColumns = @JoinColumn(name = "id_dimensiune"))
    private Set<DimensiunePizza> dimensiunePizzas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pizzas_tipuriblat_relation",
            joinColumns = @JoinColumn(name="id_pizza"),
            inverseJoinColumns = @JoinColumn(name = "id_blat"))
    private Set<TipuriBlatPizza> tipuriBlatPizzas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pizzas_sosuriblaturi_relation",
        joinColumns = @JoinColumn(name = "id_pizza"),
        inverseJoinColumns = @JoinColumn(name = "id_sos"))
    private Set<SosBlatPizza> sosBlatPizzas;

    public Pizza() {}

    public Pizza(Long idPizza, String numePizza, Double pretPizza, String urlPizza) {
        this.idPizza = idPizza;
        this.numePizza = numePizza;
        this.pretPizza = pretPizza;
        this.urlPizza = urlPizza;
    }

    public Long getIdPizza() {return idPizza;}
    public String getNumePizza() {return numePizza;}
    public Double getPretPizza() {return pretPizza;}
    public String getUrlPizza() {return urlPizza;}

    public Set<TopinguriPizza> getTopinguriPizzas() {return topinguriPizzas;}
    public Set<IngredientePizza> getIngredientePizzas() {return ingredientePizzas;}
    public Set<DimensiunePizza> getDimensiunePizzas() {return dimensiunePizzas;}
    public Set<TipuriBlatPizza> getTipuriBlatPizzas() {return tipuriBlatPizzas;}
    public Set<SosBlatPizza> getSosBlatPizzas() {return sosBlatPizzas;}

    public void setTipuriBlatPizzas(Set<TipuriBlatPizza> tipuriBlatPizzas) {this.tipuriBlatPizzas = tipuriBlatPizzas;}
    public void setIdPizza(Long idPizza) {this.idPizza = idPizza;}
    public void setNumePizza(String numePizza) {this.numePizza = numePizza;}
    public void setPretPizza(Double pretPizza) {this.pretPizza = pretPizza;}
    public void setTopinguriPizzas(Set<TopinguriPizza> topinguriPizzas) {this.topinguriPizzas = topinguriPizzas;}
    public void setIngredientePizzas(Set<IngredientePizza> ingredientePizzas) {this.ingredientePizzas = ingredientePizzas;}
    public void setDimensiunePizzas(Set<DimensiunePizza> dimensiunePizzas) {this.dimensiunePizzas = dimensiunePizzas;}
    public void setSosBlatPizzas(Set<SosBlatPizza> sosBlatPizzas) {this.sosBlatPizzas = sosBlatPizzas;}
    public void setUrlPizza(String urlPizza) {this.urlPizza = urlPizza;}

    @Override
    public String toString() {
        return "Pizza{" +
                "idPizza=" + idPizza +
                ", numePizza='" + numePizza + '\'' +
                ", pretPizza=" + pretPizza +
                ", topinguriPizzas=" + topinguriPizzas +
                ", ingredientePizzas=" + ingredientePizzas +
                ", dimensiunePizzas=" + dimensiunePizzas +
                ", tipuriBlatPizzas=" + tipuriBlatPizzas +
                ", sosBlatPizzas=" + sosBlatPizzas +
                '}';
    }
}
