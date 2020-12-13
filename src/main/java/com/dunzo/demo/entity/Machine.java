package com.dunzo.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Map;

/**
 * created by Jay Doshi
 * Date: 10/12/20
 **/

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "machine")
public class Machine {
    @JsonProperty("outlets")
    private final Outlet outlet;
    @JsonProperty("total_items_quantity")
    private final Map<String, Integer> availableIngredients;
    @JsonProperty("beverages")
    private final Map<String, Map<String, Integer>> beveragesAndRequiredIngredients;

    public Machine(@JsonProperty("outlets") Outlet outlet,
                   @JsonProperty("total_items_quantity") Map<String, Integer> availableIngredients,
                   @JsonProperty("beverages") Map<String, Map<String, Integer>> beveragesAndRequiredIngredients) {
        this.outlet = outlet;
        this.availableIngredients = availableIngredients;
        this.beveragesAndRequiredIngredients = beveragesAndRequiredIngredients;
    }

    public Outlet getOutlet() {
        return outlet;
    }

    public Map<String, Integer> getAvailableIngredients() {
        return availableIngredients;
    }

    public Map<String, Map<String, Integer>> getBeveragesAndRequiredIngredients() {
        return beveragesAndRequiredIngredients;
    }
}
