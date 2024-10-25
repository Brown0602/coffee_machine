package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Recipe;
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
    public boolean isRecipeName(String name){
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

    @Override
    public String findPopularityRecipe() {
        return recipeRepo.findPopularityRecipe();
    }

    private Recipe createRecipe(Long coffeeMachineId, RecipeDTO recipeDTO){
        Recipe recipe = new Recipe();
        Optional<CoffeeMachine> coffeeMachineOptional = coffeeMachineService.findById(coffeeMachineId);
        if (coffeeMachineOptional.isPresent()){
            CoffeeMachine coffeeMachine = coffeeMachineOptional.get();
            Set<Recipe> recipes = coffeeMachine.getRecipes();
            Set<CoffeeMachine> coffeeMachines = new HashSet<>();
            recipes.add(recipe);
            coffeeMachine.setRecipes(recipes);
            coffeeMachines.add(coffeeMachine);
            recipe.setCoffeeMachines(coffeeMachines);
        }
        recipe.setName(recipeDTO.getName());
        recipe.setIngredients(new HashSet<>(recipeDTO.getIngredients()));
        return recipe;
    }
}
