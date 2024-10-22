package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.repositories.RecipeRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultRecipeService implements RecipeService {

    private RecipeRepo recipeRepo;


    @Transactional
    @Override
    public void addRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = createRecipe(recipeDTO);
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
    public String findPopularityRecipe() {
        return recipeRepo.findPopularityRecipe();
    }

    private Recipe createRecipe(RecipeDTO recipeDTO){
        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        recipe.setIngredients(new HashSet<>(recipeDTO.getIngredients()));
        return recipe;
    }
}
