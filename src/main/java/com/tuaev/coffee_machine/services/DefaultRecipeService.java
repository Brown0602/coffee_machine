package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.IngredientDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Ingredient;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
import com.tuaev.coffee_machine.exception.NotFoundCoffeeMachineException;
import com.tuaev.coffee_machine.exception.ResourcesNotEqualIngredientsException;
import com.tuaev.coffee_machine.repositories.RecipeRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class DefaultRecipeService implements RecipeService {

    private RecipeRepo recipeRepo;
    private CoffeeMachineService coffeeMachineService;

    @Transactional
    @Override
    public void save(Long coffeeMachineId, RecipeDTO recipeDTO) {
        Recipe recipe = createRecipe(coffeeMachineId, recipeDTO);
        recipeRepo.save(recipe);
    }

    @Override
    public boolean isRecipeName(String name) {
        Optional<Recipe> recipe = recipeRepo.findByName(name);
        return recipe.isPresent();
    }

    @Override
    public Optional<Recipe> findByName(String name) {
        return recipeRepo.findByName(name);
    }

    @Override
    public List<Recipe> findAllByCoffeeMachineId(Long coffeeMachineId) {
        return recipeRepo.findAllByCoffeeMachineId(coffeeMachineId);
    }

    @Transactional
    @Override
    public String findPopularityRecipe() {
        return recipeRepo.findPopularityRecipe();
    }

    private Recipe createRecipe(Long coffeeMachineId, RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        Set<Ingredient> ingredients = new HashSet<>();
        CoffeeMachine coffeeMachine = coffeeMachineService.findById(coffeeMachineId).orElseThrow(NotFoundCoffeeMachineException::new);
        Set<Resource> resources = coffeeMachine.getResources();
        if (!resources.stream().allMatch(resource ->
                recipeDTO.getIngredients().stream().allMatch(ingredient ->
                        ingredient.getName().equals(resource.getType())))){
            throw new ResourcesNotEqualIngredientsException();
        }
        Set<Recipe> recipes = coffeeMachine.getRecipes();
        Set<CoffeeMachine> coffeeMachines = new HashSet<>();
        recipes.add(recipe);
        coffeeMachine.setRecipes(recipes);
        coffeeMachines.add(coffeeMachine);
        recipe.setCoffeeMachines(coffeeMachines);
        recipe.setName(recipeDTO.getName());
        for (IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientDTO.getName());
            ingredient.setAmount(ingredientDTO.getAmount());
            ingredients.add(ingredient);
        }
        recipe.setIngredients(ingredients);
        return recipe;
    }
}
