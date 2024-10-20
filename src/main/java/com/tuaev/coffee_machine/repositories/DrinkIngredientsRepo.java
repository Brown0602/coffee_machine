package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.Drink;
import com.tuaev.coffee_machine.entity.DrinkIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DrinkIngredientsRepo extends JpaRepository<DrinkIngredients, Long> {

    @Query(name = "SELECT * FROM drink_ingredients WHERE drink_id = :drink_id", nativeQuery = true)
    List<DrinkIngredients> findAllByIdDrinkId(@Param("drink_id")Drink drink);
}
