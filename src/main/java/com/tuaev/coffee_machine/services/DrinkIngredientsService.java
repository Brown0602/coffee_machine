package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.RecipeIngredients;

import java.util.List;

public interface DrinkIngredientsService {

    List<RecipeIngredients> getAllByIdDrinkId(Recipe drink);
}
