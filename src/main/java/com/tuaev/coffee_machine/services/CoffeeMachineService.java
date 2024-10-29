package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Recipe;
import java.util.List;
import java.util.Optional;

public interface CoffeeMachineService {

    Optional<CoffeeMachine> findById(Long id);

    CoffeeMachine save(CoffeeMachineDTO coffeeMachineDTO);

    CoffeeMachine updateResources(Long id, List<ResourceDTO> resourceDTOS);

    Recipe addRecipe(Long coffeeMachineId, RecipeDTO recipeDTO);
}
