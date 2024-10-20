package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Drink;
import com.tuaev.coffee_machine.entity.DrinkIngredients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrinkIngredientsService {

    List<DrinkIngredients> getAllByIdDrinkId(Drink drink);
}
