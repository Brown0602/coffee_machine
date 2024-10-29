package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
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
    private ResourceService resourceService;
    private IngredientService ingredientService;

    @Transactional
    @Override
    public void save(CoffeeMachine coffeeMachine, RecipeDTO recipeDTO) {
        resourceService.isResourcesEqualIngredientsInRecipe(coffeeMachine.getResources(), recipeDTO.getIngredients());
        Recipe recipe = createRecipe(coffeeMachine, recipeDTO);
        recipeRepo.save(recipe);
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

    private Recipe createRecipe(CoffeeMachine coffeeMachine, RecipeDTO recipeDTO) {
        Set<Resource> resources = coffeeMachine.getResources();
        Set<CoffeeMachine> coffeeMachines = new HashSet<>();
        coffeeMachines.add(coffeeMachine);
        if (!resources.stream().allMatch(resource ->
                recipeDTO.getIngredients().stream().allMatch(ingredient ->
                        ingredient.getName().equals(resource.getType())))){
            throw new ResourcesNotEqualIngredientsException();
        }
        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        recipe.setCoffeeMachines(coffeeMachines);
        recipe.setIngredients(ingredientService.buildIngredients(recipeDTO));
        return recipe;
    }
}
