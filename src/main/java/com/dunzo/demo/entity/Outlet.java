package com.dunzo.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * created by Jay Doshi
 * Date: 10/12/20
 **/

public class Outlet {
    @JsonProperty("count_n")
    int numberOfOutlets;

    public Outlet(@JsonProperty("count_n") int numberOfOutlets) {
        this.numberOfOutlets = numberOfOutlets;
    }

    public int getNumberOfOutlets() {
        return numberOfOutlets;
    }
}
