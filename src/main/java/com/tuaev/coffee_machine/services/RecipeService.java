package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Recipe;
import java.util.List;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> buildRecipes(List<RecipeDTO> recipeDTOS);

    Recipe getRecipeByName(OrderDTO orderDTO, CoffeeMachine coffeeMachine);

    String findPopularityRecipe();

    void checkRecipeDuplicate(CoffeeMachine coffeeMachine, RecipeDTO recipeDTO);

}
