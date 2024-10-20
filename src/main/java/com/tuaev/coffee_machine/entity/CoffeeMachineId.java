package com.tuaev.coffee_machine.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class CoffeeMachineId {

    private Long coffeeMachineId;
    private Long recipeId;
}
