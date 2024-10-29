package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {

    @Query(value = """
            SELECT i.id, i.name, i.amount\s
            FROM recipe r
            JOIN coffee_machine_recipe cmr ON r.id = cmr.recipe_id
            JOIN coffee_machine cm ON cmr.coffee_machine_id = cm.id
            JOIN recipe_ingredients ri ON r.id = ri.recipe_id
            JOIN ingredients i ON ri.ingredients_id = i.id
            WHERE r.name = :recipeName""", nativeQuery = true)
    List<Ingredient> findAllIngredientsByRecipeName(@Param("recipeName") String recipeName);
}
