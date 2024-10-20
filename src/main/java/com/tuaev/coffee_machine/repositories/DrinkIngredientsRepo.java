package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkIngredientsRepo extends JpaRepository<RecipeIngredients, Long> {

}
