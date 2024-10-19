package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.DrinkIngredients;
import com.tuaev.coffee_machine.repositories.DrinkIngredientsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultDrinkIngredientsService implements DrinkIngredientsService{

    private DrinkIngredientsRepo drinkIngredientsRepo;

    @Override
    public List<DrinkIngredients> getAll() {
        return drinkIngredientsRepo.findAll();
    }
}
