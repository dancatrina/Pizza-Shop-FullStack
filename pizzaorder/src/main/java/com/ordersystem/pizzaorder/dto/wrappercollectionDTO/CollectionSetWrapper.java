package com.ordersystem.pizzaorder.dto.wrappercollectionDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionSetWrapper<T> {

    @JsonProperty("collection")
    private Set<T> collection;

    public CollectionSetWrapper(LinkedHashSet<T> collection) {
        this.collection = collection;
    }

    public CollectionSetWrapper() {}

    public Set<T> getCollection() {
        return collection;
    }

    public void setCollection(Set<T> collection) {this.collection = collection;}
}
