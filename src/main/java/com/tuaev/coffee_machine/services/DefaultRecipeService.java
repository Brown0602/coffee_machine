package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.exception.NotRecipeByNameException;
import com.tuaev.coffee_machine.exception.RecipeAlreadyExistsException;
import com.tuaev.coffee_machine.repositories.RecipeRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DefaultRecipeService implements RecipeService {

    private RecipeRepo recipeRepo;
    private IngredientService ingredientService;

    @Override
    public void isRecipeDuplicate(CoffeeMachine coffeeMachine, RecipeDTO recipeDTO) {
        if (coffeeMachine.getRecipes().stream().anyMatch(recipe ->
                recipe.getName().equals(recipeDTO.getName()))){
            throw new RecipeAlreadyExistsException();
        }
    }


    @Override
    public Set<Recipe> buildRecipes(List<RecipeDTO> recipeDTOS) {
        Set<Recipe> recipes = new HashSet<>();
        for (RecipeDTO recipeDTO : recipeDTOS) {
            Recipe recipe = new Recipe();
            recipe.setName(recipeDTO.getName());
            recipe.setIngredients(ingredientService.buildIngredients(recipeDTO));
            recipes.add(recipe);
        }
        return recipes;
    }

    @Override
    public Recipe getRecipeByName(OrderDTO orderDTO, CoffeeMachine coffeeMachine) {
        return coffeeMachine.getRecipes().stream()
                .filter(recipe1 -> recipe1.getName().equals(orderDTO.getCoffeeName()))
                .findFirst()
                .orElseThrow(NotRecipeByNameException::new);
    }

    @Transactional
    @Override
    public String findPopularityRecipe() {
        return recipeRepo.findMostPopularRecipe();
    }
}
