package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.CoffeeMachine;

import java.util.Optional;

public interface CoffeeMachineService {

    Optional<CoffeeMachine> getCoffeeMachine();

    String findPopularDrink();
}
