package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.IngredientDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
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

@AllArgsConstructor
@Service
public class DefaultResourceService implements ResourceService {

    private ResourceRepo resourceRepo;

    @Override
    public List<Resource> findByCoffeeMachineId(Long coffeeMachineId) {
        return resourceRepo.findByCoffeeMachineId(coffeeMachineId);
    }

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
    public void isResourcesEqualIngredientsInRecipe(Set<Resource> resources, List<IngredientDTO> ingredientDTOS) {
        if (ingredientDTOS.stream().anyMatch(ingredient ->
                        resources.stream().noneMatch(resource ->
                                resource.getType().equals(ingredient.getName())))){
            throw new ResourcesNotEqualIngredientsException();
        }
    }

    @Override
    public void isResourcesEqualIngredientsInRecipes(Set<Resource> resources, Set<Recipe> recipes) {
        if (recipes.stream().noneMatch(recipe ->
                recipe.getIngredients().stream().allMatch(ingredient ->
                resources.stream().anyMatch(resource ->
                        resource.getType().equals(ingredient.getName()))))){
            throw new ResourcesNotEqualIngredientsException();
        }
    }

    @Override
    public void isMachineHasEnoughResources(Set<Resource> coffeeMachineResources, List<Ingredient> ingredientsByRecipeName) {
        if (!coffeeMachineResources.stream().allMatch(
                resource ->
                        ingredientsByRecipeName.stream().allMatch(ingredient ->
                                ingredient.getAmount() <= resource.getAmount()))){
            throw new NotEnoughResourcesException();
        }
    }
}
