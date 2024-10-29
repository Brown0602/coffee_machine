package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.IngredientDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Ingredient;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
import com.tuaev.coffee_machine.exception.NotEnoughResourcesException;
import com.tuaev.coffee_machine.exception.ResourcesNotEqualIngredientsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DefaultResourceService implements ResourceService {

    @Override
    public Set<Resource> buildResources(List<ResourceDTO> resourceDTOS) {
        return resourceDTOS.stream().map(resourceDTO -> new Resource(resourceDTO.getType(),
                resourceDTO.getAmount())).collect(Collectors.toSet());
    }

    @Override
    public Set<Resource> updateResources(Set<Resource> coffeeMachineResources, List<ResourceDTO> resourceDTOS) {
        coffeeMachineResources.forEach(coffeeMachineResource ->
                resourceDTOS.stream()
                        .filter(resourceDTO -> coffeeMachineResource.getType().equals(resourceDTO.getType()))
                        .findFirst()
                        .ifPresent(resourceDTO -> coffeeMachineResource.setAmount(coffeeMachineResource.getAmount() + resourceDTO.getAmount())));
        return coffeeMachineResources;
    }

    @Override
    public void checkResourcesEqualIngredientsInRecipe(Set<Resource> coffeeMachineResources, List<IngredientDTO> ingredientDTOS) {
        if (ingredientDTOS.stream().anyMatch(ingredient ->
                coffeeMachineResources.stream().noneMatch(resource ->
                        resource.getType().equals(ingredient.getName())))) {
            throw new ResourcesNotEqualIngredientsException("Ресурсы не равны ингредиентам");
        }
    }

    @Override
    public void checkResourcesEqualIngredientsInRecipes(Set<Resource> resources, Set<Recipe> recipes) {
        if (recipes.stream().noneMatch(recipe ->
                recipe.getIngredients().stream().allMatch(ingredient ->
                        resources.stream().anyMatch(resource ->
                                resource.getType().equals(ingredient.getName()))))) {
            throw new ResourcesNotEqualIngredientsException("Ресурсы не равны ингредиентам");
        }
    }

    @Override
    public void checkSufficientResourcesForRecipe(Set<Resource> coffeeMachineResources, Set<Ingredient> recipeIngredients) {
        if (recipeIngredients.stream().anyMatch(
                ingredient ->
                        coffeeMachineResources.stream()
                                .noneMatch(resource ->
                                        resource.getType().equals(ingredient.getName()) &&
                                                resource.getAmount() >= ingredient.getAmount()))) {
            throw new NotEnoughResourcesException("Не хватает ресурсов");
        }
    }

    @Override
    public Set<Resource> updateResourcesForRecipe(Recipe recipe, Set<Resource> resources) {
        for (Resource resource : resources) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (ingredient.getName().equals(resource.getType())) {
                    resource.setAmount(resource.getAmount() - ingredient.getAmount());
                }
            }
        }
        return resources;
    }

    @Override
    public Set<Resource> getResourcesByCoffeeMachine(CoffeeMachine coffeeMachine, Recipe recipe) {
        return coffeeMachine.getResources().stream()
                .filter(resource ->
                        recipe.getIngredients().stream().anyMatch(ingredient ->
                                ingredient.getName().equals(resource.getType()))).collect(Collectors.toSet());
    }
}
