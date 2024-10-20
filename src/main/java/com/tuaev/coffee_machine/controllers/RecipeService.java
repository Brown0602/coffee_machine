package com.tuaev.coffee_machine.controllers;

import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public void addRecipe(RecipeDto recipeDTO){
        recipeRepository.addRecipe(new Recipe());
    }
}
