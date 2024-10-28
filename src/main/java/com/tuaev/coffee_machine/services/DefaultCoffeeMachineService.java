package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.dto.IngredientDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Ingredient;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
import com.tuaev.coffee_machine.repositories.CoffeeMachineRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class DefaultCoffeeMachineService implements CoffeeMachineService {

    private CoffeeMachineRepo coffeeMachineRepo;

    @Override
    public Optional<CoffeeMachine> findById(Long id) {
        return coffeeMachineRepo.findById(id);
    }

    @Transactional
    @Override
    public ResponseEntity<String> save(CoffeeMachineDTO coffeeMachineDTO) {
        coffeeMachineRepo.save(createCoffeeMachine(coffeeMachineDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body("Кофемашина создана");
    }

    @Transactional
    @Override
    public void updateResources(Long id, List<ResourceDTO> resourceDTOS) {
        Optional<CoffeeMachine> coffeeMachine = coffeeMachineRepo.findById(id);
        if (coffeeMachine.isPresent()) {
            Set<Resource> coffeeMachineResources = coffeeMachine.get().getResources();
            for (Resource coffeeMachineResource : coffeeMachineResources) {
                for (ResourceDTO resourceDTO : resourceDTOS) {
                    if (coffeeMachineResource.getType().equals(resourceDTO.getType())) {
                        coffeeMachineResource.setAmount(coffeeMachineResource.getAmount() + resourceDTO.getAmount());
                        break;
                    }
                }
            }
            coffeeMachineRepo.save(coffeeMachine.get());
        }
    }

    private CoffeeMachine createCoffeeMachine(CoffeeMachineDTO coffeeMachineDTO) {
        Set<Resource> resources = new HashSet<>();
        Set<Recipe> recipes = new HashSet<>();
        Set<Ingredient> ingredients = new HashSet<>();
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setModel(coffeeMachineDTO.getModel());
        for (ResourceDTO resourceDTO : coffeeMachineDTO.getResources()) {
            Resource resource = new Resource();
            resource.setType(resourceDTO.getType());
            resource.setAmount(resourceDTO.getAmount());
            resources.add(resource);
        }
        for (RecipeDTO recipeDTO : coffeeMachineDTO.getRecipes()) {
            Recipe recipe = new Recipe();
            recipe.setName(recipeDTO.getName());
            for (IngredientDTO ingredientDTO :recipeDTO.getIngredients()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientDTO.getName());
                ingredient.setAmount(ingredientDTO.getAmount());
                ingredients.add(ingredient);
            }
            recipe.setIngredients(ingredients);
            ingredients.clear();
            recipes.add(recipe);
        }

        coffeeMachine.setResources(resources);
        coffeeMachine.setRecipes(recipes);
        return coffeeMachine;
    }
}

