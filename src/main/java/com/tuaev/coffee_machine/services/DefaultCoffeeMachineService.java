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
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@NoArgsConstructor
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
            CoffeeMachine coffeeMachine = coffeeMachineRepo.findById(id).orElseThrow(()->
                    new NotFoundCoffeeMachineException("Нет такой кофемашины"));
            coffeeMachine.setResources(resourceService.updateResources(coffeeMachine.getResources(), resourceDTOS));
            return coffeeMachineRepo.save(coffeeMachine);
    }

    @Transactional
    @Override
    public Recipe addRecipe(Long coffeeMachineId, RecipeDTO recipeDTO) {
        CoffeeMachine coffeeMachine = findById(coffeeMachineId).orElseThrow(()->
                new NotFoundCoffeeMachineException("Нет такой кофемашины"));
        Set<Recipe> recipes = coffeeMachine.getRecipes();
        recipeService.checkRecipeDuplicate(coffeeMachine, recipeDTO);
        resourceService.checkResourcesEqualIngredientsInRecipe(coffeeMachine.getResources(), recipeDTO.getIngredients());
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
        resourceService.checkResourcesEqualIngredientsInRecipes(resources, recipes);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setModel(coffeeMachineDTO.getModel());
        coffeeMachine.setRecipes(recipes);
        coffeeMachine.setResources(resources);
        return coffeeMachine;
    }
}

