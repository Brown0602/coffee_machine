package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.IngredientDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
import com.tuaev.coffee_machine.entity.Ingredient;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
import java.util.List;
import java.util.Set;

public interface ResourceService {

    List<Resource> findByCoffeeMachineId(Long coffeeMachineId);

    Set<Resource> buildResources(List<ResourceDTO> resourceDTOS);

    Set<Resource> updateResources(Set<Resource> coffeeMachineResources, List<ResourceDTO> resourceDTOS);

    void isResourcesEqualIngredientsInRecipe(Set<Resource> resources, List<IngredientDTO> ingredientDTOS);

    void isResourcesEqualIngredientsInRecipes(Set<Resource> resources, Set<Recipe> recipes);

    void isMachineHasEnoughResources(Set<Resource> coffeeMachineResources, List<Ingredient> ingredientsByRecipeName);
}