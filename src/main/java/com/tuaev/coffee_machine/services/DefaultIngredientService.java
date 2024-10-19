package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Ingredient;
import com.tuaev.coffee_machine.repositories.IngredientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultIngredientService implements IngredientService{

    private IngredientRepo ingredientRepo;


    @Override
    public List<Ingredient> ingredients() {
        return ingredientRepo.findAll();
    }
}
