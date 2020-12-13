package com.dunzo.demo;

import com.dunzo.demo.entity.Machine;
import com.dunzo.demo.service.MachineManager;
import com.dunzo.demo.service.MachineService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created by Jay Doshi
 * Date: 10/12/20
 **/
public class MachineServiceTest {

    @Test
    public void test1() throws IOException {
        InputStream inputStream = new ClassPathResource("input.json").getInputStream();
        Machine machine = new ObjectMapper().readValue(inputStream, new TypeReference<Machine>() {
        });

        MachineManager.addIngredients(machine.getAvailableIngredients());
        ExecutorService executorService = Executors.newFixedThreadPool(machine.getOutlet().getNumberOfOutlets());
        executorService.execute(new MachineService(machine.getBeveragesAndRequiredIngredients(), "hot_tea"));
        executorService.execute(new MachineService(machine.getBeveragesAndRequiredIngredients(), "hot_coffee"));
        executorService.execute(new MachineService(machine.getBeveragesAndRequiredIngredients(), "green_tea"));
        executorService.execute(new MachineService(machine.getBeveragesAndRequiredIngredients(), "black_tea"));
    }

    @Test
    public void test2() throws IOException, InterruptedException {
        InputStream inputStream = new ClassPathResource("input.json").getInputStream();
        Machine machine = new ObjectMapper().readValue(inputStream, new TypeReference<Machine>() {
        });
        ExecutorService executorService = Executors.newFixedThreadPool(machine.getOutlet().getNumberOfOutlets());
        executorService.execute(new MachineService(machine.getBeveragesAndRequiredIngredients(), "hot_tea"));
        executorService.execute(new MachineService(machine.getBeveragesAndRequiredIngredients(), "hot_coffee"));
        executorService.execute(new MachineService(machine.getBeveragesAndRequiredIngredients(), "green_tea"));
        executorService.execute(new MachineService(machine.getBeveragesAndRequiredIngredients(), "black_tea"));
        MachineManager.addIngredients(Collections.singletonMap("hot_water", 200));
        executorService.execute(new MachineService(machine.getBeveragesAndRequiredIngredients(), "hot_tea"));
    }


}
