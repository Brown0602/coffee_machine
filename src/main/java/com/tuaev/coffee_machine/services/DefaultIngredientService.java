package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.IngredientDTO;
import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DefaultIngredientService implements IngredientService {

    @Override
    public Set<Ingredient> buildIngredients(RecipeDTO recipeDTO) {
        Set<Ingredient> ingredients = new HashSet<>();
        for (IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientDTO.getName());
            ingredient.setAmount(ingredientDTO.getAmount());
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    @Override
    public Set<Ingredient> getIngredientsByRecipeName(OrderDTO orderDTO, CoffeeMachine coffeeMachine) {
        return coffeeMachine.getRecipes().stream()
                .filter(recipe -> recipe.getName().equals(orderDTO.getCoffeeName()))
                .flatMap(recipe -> recipe.getIngredients().stream()).collect(Collectors.toSet());
    }
}
