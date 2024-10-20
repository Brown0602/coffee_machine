package com.tuaev.coffee_machine.controllers;

import com.tuaev.coffee_machine.dto.RecipeDTO;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public void addRecipe(RecipeDTO recipeDTO){
        recipeRepository.addRecipe(new Recipe());
    }
}
