package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Recipe;

import java.util.Optional;

public interface DrinkService {

    Optional<Recipe> getByName(String name);
}
