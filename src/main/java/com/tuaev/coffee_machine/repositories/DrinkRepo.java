package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkRepo extends JpaRepository<Recipe, Long> {

    @Query(name = "SELECT * FROM drinks WHERE name = :name", nativeQuery = true)
    Optional<Recipe> findByName(@Param("name") String name);
}
