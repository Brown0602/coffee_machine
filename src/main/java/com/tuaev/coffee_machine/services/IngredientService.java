package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Ingredient;
import java.util.List;

public interface IngredientService {

    List<Ingredient> findAllIngredientsByRecipeName(String recipeName);
}
