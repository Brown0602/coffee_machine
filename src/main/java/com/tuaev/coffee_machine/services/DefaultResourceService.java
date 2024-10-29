package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.IngredientDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Ingredient;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
import com.tuaev.coffee_machine.exception.NotEnoughResourcesException;
import com.tuaev.coffee_machine.exception.ResourcesNotEqualIngredientsException;
import com.tuaev.coffee_machine.repositories.ResourceRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DefaultResourceService implements ResourceService {

    private ResourceRepo resourceRepo;

    @Override
    public Set<Resource> buildResources(List<ResourceDTO> resourceDTOS) {
        Set<Resource> resources = new HashSet<>();
        for (ResourceDTO resourceDTO : resourceDTOS) {
            Resource resource = new Resource();
            resource.setType(resourceDTO.getType());
            resource.setAmount(resourceDTO.getAmount());
            resources.add(resource);
        }
        return resources;
    }

    @Override
    public Set<Resource> updateResources(Set<Resource> coffeeMachineResources, List<ResourceDTO> resourceDTOS) {
        for (Resource coffeeMachineResource : coffeeMachineResources) {
            for (ResourceDTO resourceDTO : resourceDTOS) {
                if (coffeeMachineResource.getType().equals(resourceDTO.getType())) {
                    coffeeMachineResource.setAmount(coffeeMachineResource.getAmount() + resourceDTO.getAmount());
                    break;
                }
            }
        }
        return coffeeMachineResources;
    }

    @Override
    public void isResourcesEqualIngredientsInRecipe(Set<Resource> coffeeMachineResources, List<IngredientDTO> ingredientDTOS) {
        if (ingredientDTOS.stream().anyMatch(ingredient ->
                coffeeMachineResources.stream().noneMatch(resource ->
                        resource.getType().equals(ingredient.getName())))) {
            throw new ResourcesNotEqualIngredientsException();
        }
    }

    @Override
    public void isResourcesEqualIngredientsInRecipes(Set<Resource> resources, Set<Recipe> recipes) {
        if (recipes.stream().noneMatch(recipe ->
                recipe.getIngredients().stream().allMatch(ingredient ->
                        resources.stream().anyMatch(resource ->
                                resource.getType().equals(ingredient.getName()))))) {
            throw new ResourcesNotEqualIngredientsException();
        }
    }

    public void hasSufficientResourcesForRecipe(Set<Resource> coffeeMachineResources, Set<Ingredient> recipeIngredients) {
        if (recipeIngredients.stream().anyMatch(
                ingredient ->
                        coffeeMachineResources.stream()
                                .noneMatch(resource ->
                                        resource.getType().equals(ingredient.getName()) &&
                                                resource.getAmount() >= ingredient.getAmount()))) {
            throw new NotEnoughResourcesException();
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
