package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    @Query(value = "SELECT * FROM recipe WHERE name = :name", nativeQuery = true)
    Optional<Recipe> findByName(@Param("name") String name);

    @Query(value = "SELECT r.name FROM recipe r JOIN orders o ON r.id = o.recipe_id GROUP BY r.name ORDER BY COUNT(o.id) DESC LIMIT 1", nativeQuery = true)
    String findPopularityRecipe();

    @Query(value = "select r.id, r.name from recipe r inner join coffee_machine_recipe cmr on r.id = cmr.recipe_id inner join coffee_machine cm on cmr.coffee_machine_id = cm.id where cm.id = :coffee_machine_id", nativeQuery = true)
    List<Recipe> findAllByCoffeeMachineId(@Param("coffee_machine_id") Long coffeeMachineId);

}
