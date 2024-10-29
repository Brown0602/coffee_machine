package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.IngredientDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.Ingredient;
import com.tuaev.coffee_machine.repositories.IngredientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class DefaultIngredientService implements IngredientService {

    private IngredientRepo ingredientRepo;
    @Override
    public List<Ingredient> findAllIngredientsByRecipeName(String recipeName) {
        return ingredientRepo.findAllIngredientsByRecipeName(recipeName);
    }

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
}
