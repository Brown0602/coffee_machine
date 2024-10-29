package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
import com.tuaev.coffee_machine.exception.NotFoundCoffeeMachineException;
import com.tuaev.coffee_machine.repositories.CoffeeMachineRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class DefaultCoffeeMachineService implements CoffeeMachineService {

    private CoffeeMachineRepo coffeeMachineRepo;
    private RecipeService recipeService;
    private ResourceService resourceService;
    private IngredientService ingredientService;

    @Override
    public Optional<CoffeeMachine> findById(Long id) {
        return coffeeMachineRepo.findById(id);
    }

    @Transactional
    @Override
    public CoffeeMachine save(CoffeeMachineDTO coffeeMachineDTO) {
        return coffeeMachineRepo.save(createCoffeeMachine(coffeeMachineDTO));
    }

    @Transactional
    @Override
    public CoffeeMachine updateResources(Long id, List<ResourceDTO> resourceDTOS) {
            CoffeeMachine coffeeMachine = coffeeMachineRepo.findById(id).orElseThrow(NotFoundCoffeeMachineException::new);
            coffeeMachine.setResources(resourceService.updateResources(coffeeMachine.getResources(), resourceDTOS));
            return coffeeMachineRepo.save(coffeeMachine);
    }

    @Override
    public Recipe addRecipe(Long coffeeMachineId, RecipeDTO recipeDTO) {
        CoffeeMachine coffeeMachine = findById(coffeeMachineId).orElseThrow(NotFoundCoffeeMachineException::new);
        Set<Recipe> recipes = coffeeMachine.getRecipes();
        recipeService.isRecipeDuplicate(coffeeMachine, recipeDTO);
        resourceService.isResourcesEqualIngredientsInRecipe(coffeeMachine.getResources(), recipeDTO.getIngredients());
        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        recipe.setIngredients(ingredientService.buildIngredients(recipeDTO));
        recipes.add(recipe);
        coffeeMachine.setRecipes(recipes);
        coffeeMachineRepo.save(coffeeMachine);
        return recipe;
    }

    private CoffeeMachine createCoffeeMachine(CoffeeMachineDTO coffeeMachineDTO) {
        Set<Resource> resources = resourceService.buildResources(coffeeMachineDTO.getResources());
        Set<Recipe> recipes = recipeService.buildRecipes(coffeeMachineDTO.getRecipes());
        resourceService.isResourcesEqualIngredientsInRecipes(resources, recipes);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setModel(coffeeMachineDTO.getModel());
        coffeeMachine.setRecipes(recipes);
        coffeeMachine.setResources(resources);
        return coffeeMachine;
    }
}

