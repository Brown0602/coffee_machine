package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    void save(Long coffeeMachineId, RecipeDTO recipeDTO);

    boolean isRecipeName(String name);

    Optional<Recipe> findByName(String name);

    List<Recipe> findAllByCoffeeMachineId(Long coffeeMachineId);

    String findPopularityRecipe();

}
