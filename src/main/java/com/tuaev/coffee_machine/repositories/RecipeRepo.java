package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    @Query(value = "SELECT r.name FROM recipe r JOIN orders o ON r.id = o.recipe_id GROUP BY r.name ORDER BY COUNT(o.id) DESC LIMIT 1", nativeQuery = true)
    String findMostPopularRecipe();
}
