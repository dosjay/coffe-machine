package com.dunzo.demo.service;

import com.dunzo.demo.entity.Status;
import com.dunzo.demo.utils.Pair;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by Jay Doshi
 * Date: 10/12/20
 **/
public class MachineManager {
    public static final ConcurrentMap<String, AtomicInteger> contents = new ConcurrentHashMap<>();


    public static void addIngredients(Map<String, Integer> ingredients) {
        ingredients.entrySet().forEach(ingredient -> contents.putIfAbsent(ingredient.getKey(), new AtomicInteger(ingredient.getValue())));
    }

    public static Pair<String, Status> getBeverage(Map<String, Integer> beverageContents) {
        Set<String> ingredientsReserved = new HashSet<>();

        for (Map.Entry<String, Integer> ingredient : beverageContents.entrySet()) {
            Status status = getIngredient(ingredient.getKey(), ingredient.getValue());
            if (status.equals(Status.AVAILABLE)) {
                ingredientsReserved.add(ingredient.getKey());
            } else {
                for (String ingredientReserved : ingredientsReserved) {
                    addBack(ingredientReserved, beverageContents.get(ingredientReserved));
                }
                return new Pair(ingredient.getKey(), status);
            }

        }
        updateContents(beverageContents);
        return new Pair<>("", Status.AVAILABLE);
    }

    private static Status getIngredient(String ingredient, Integer quantity) {
        if (contents.containsKey(ingredient)) {
            synchronized (contents.get(ingredient)) {
                if (contents.get(ingredient).get() >= quantity) {
                    return Status.AVAILABLE;
                }
                return Status.INSUFFICIENT;
            }
        }
        return Status.UNAVAILABLE;

    }

    private static void addBack(String ingredientReserved, Integer quantity) {
        synchronized (contents.get(ingredientReserved)) {
            contents.get(ingredientReserved).addAndGet(quantity);
        }
    }

    private static void updateContents(Map<String, Integer> beverageContents) {
        for (Map.Entry<String, Integer> ingredient : beverageContents.entrySet()) {
            synchronized (contents.get(ingredient.getKey())) {
                if (contents.containsKey(ingredient.getKey()))
                    contents.get(ingredient.getKey()).addAndGet(-1 * ingredient.getValue());
            }
        }
    }

    public static void main(String[] args) {

    }
}
