package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {

    @Query(value = "SELECT i.id, i.name, i.amount \n" +
            "FROM recipe r\n" +
            "JOIN coffee_machine_recipe cmr ON r.id = cmr.recipe_id\n" +
            "JOIN coffee_machine cm ON cmr.coffee_machine_id = cm.id\n" +
            "JOIN recipe_ingredients ri ON r.id = ri.recipe_id\n" +
            "JOIN ingredients i ON ri.ingredients_id = i.id\n" +
            "WHERE r.name = :recipeName", nativeQuery = true)
    List<Ingredient> findAllIngredientsByRecipeName(@Param("recipeName") String recipeName);
}
