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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultRecipeService implements RecipeService {

    private RecipeRepo recipeRepo;
    private IngredientService ingredientService;

    @Override
    public void checkRecipeDuplicate(CoffeeMachine coffeeMachine, RecipeDTO recipeDTO) {
        if (coffeeMachine.getRecipes().stream().anyMatch(recipe ->
                recipe.getName().equals(recipeDTO.getName()))){
            throw new RecipeAlreadyExistsException("Такой рецепт уже есть");
        }
    }


    @Override
    public Set<Recipe> buildRecipes(List<RecipeDTO> recipeDTOS) {
        return recipeDTOS.stream().map(recipeDTO -> new Recipe(recipeDTO.getName(),
                ingredientService.buildIngredients(recipeDTO))).collect(Collectors.toSet());
    }

    @Override
    public Recipe getRecipeByName(OrderDTO orderDTO, CoffeeMachine coffeeMachine) {
        return coffeeMachine.getRecipes().stream()
                .filter(recipe -> recipe.getName().equals(orderDTO.getCoffeeName()))
                .findFirst()
                .orElseThrow(() -> new NotRecipeByNameException("Нет такого рецепта"));
    }

    @Transactional
    @Override
    public String findPopularityRecipe() {
        return recipeRepo.findMostPopularRecipe();
    }
}
