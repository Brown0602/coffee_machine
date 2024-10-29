package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Ingredient;
import java.util.Set;

public interface IngredientService {

    Set<Ingredient> getIngredientsByRecipeName(OrderDTO orderDTO, CoffeeMachine coffeeMachine);

    Set<Ingredient> buildIngredients(RecipeDTO recipeDTO);
}
