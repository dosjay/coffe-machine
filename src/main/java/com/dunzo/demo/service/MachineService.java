package com.dunzo.demo.service;

import com.dunzo.demo.entity.Status;
import com.dunzo.demo.utils.Pair;
import lombok.Data;

import java.util.Map;

/**
 * created by Jay Doshi
 * Date: 10/12/20
 **/

@Data
public class MachineService implements Runnable {

    private Map<String, Map<String, Integer>> beverages;
    private String beverageName;

    public MachineService(Map<String, Map<String, Integer>> beverages, String beverageName) {
        this.beverages = beverages;
        this.beverageName = beverageName;
    }

    public void makeBeverage() {
        Pair<String, Status> result = MachineManager.getBeverage(beverages.get(beverageName));
        switch (result.getValue()) {
            case AVAILABLE: {
                synchronized (System.out) {
                    System.out.println(beverageName + " is prepared");
                }
                break;
            }
            case UNAVAILABLE: {
                synchronized (System.out) {
                    System.out.println(beverageName + " cannot be prepared because " + result.getKey() + " is not available");
                }
                break;
            }
            case INSUFFICIENT: {
                synchronized (System.out) {
                    System.out.println(beverageName + " cannot be prepared because " + result.getKey() + " is not sufficient");
                }
                break;
            }
        }
    }

    @Override
    public void run() {
        if (beverages.containsKey(beverageName)) {
            makeBeverage();
        } else {
            throw new RuntimeException("Beverage" + beverageName + " Not found");
        }
    }

}
