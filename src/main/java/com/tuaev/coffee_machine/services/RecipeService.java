package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.Recipe;

import java.util.Optional;

public interface RecipeService {

    void addRecipe(RecipeDTO recipeDTO);

    boolean isRecipeName(String name);

    Optional<Recipe> findByName(String name);

    String findPopularityRecipe();

}
