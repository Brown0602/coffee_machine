package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.IngredientDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Ingredient;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
import java.util.List;
import java.util.Set;

public interface ResourceService {

    Set<Resource> buildResources(List<ResourceDTO> resourceDTOS);

    Set<Resource> updateResources(Set<Resource> coffeeMachineResources, List<ResourceDTO> resourceDTOS);

    void checkResourcesEqualIngredientsInRecipe(Set<Resource> resources, List<IngredientDTO> ingredientDTOS);

    void checkResourcesEqualIngredientsInRecipes(Set<Resource> resources, Set<Recipe> recipes);

    void checkSufficientResourcesForRecipe(Set<Resource> coffeeMachineResources, Set<Ingredient> recipeIngredients);

    Set<Resource> getResourcesByCoffeeMachine(CoffeeMachine coffeeMachine, Recipe recipe);

    Set<Resource> updateResourcesForRecipe(Recipe recipe, Set<Resource> resources);
}