package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.RecipeIngredients;
import com.tuaev.coffee_machine.repositories.DrinkIngredientsRepo;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultDrinkIngredientsService implements DrinkIngredientsService{

    private DrinkIngredientsRepo drinkIngredientsRepo;


    @Override
    public List<RecipeIngredients> getAllByIdDrinkId(Recipe drink) {
        return Collections.emptyList();
//        return drinkIngredientsRepo.findAllByIdDrinkId(drink);
    }
}
