package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Drink;

import java.util.Optional;

public interface DrinkService {

    Optional<Drink> getByName(String name);
}
