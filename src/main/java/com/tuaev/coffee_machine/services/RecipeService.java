package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Recipe;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    void save(CoffeeMachine coffeeMachine, RecipeDTO recipeDTO);

    Set<Recipe> buildRecipes(List<RecipeDTO> recipeDTOS);

    boolean isRecipeName(String name);

    Optional<Recipe> findByName(String name);

    List<Recipe> findAllByCoffeeMachineId(Long coffeeMachineId);

    String findPopularityRecipe();

}
