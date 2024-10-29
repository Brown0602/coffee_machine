package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.Ingredient;
import java.util.List;
import java.util.Set;

public interface IngredientService {

    List<Ingredient> findAllIngredientsByRecipeName(String recipeName);

    Set<Ingredient> buildIngredients(RecipeDTO recipeDTO);
}
