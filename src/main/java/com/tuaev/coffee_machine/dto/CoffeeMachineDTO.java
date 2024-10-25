package com.tuaev.coffee_machine.dto;

import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
import lombok.Getter;
import java.util.List;

@Getter
public class CoffeeMachineDTO {

    private String model;
    private List<Resource> resources;
    private List<Recipe> recipes;
}
