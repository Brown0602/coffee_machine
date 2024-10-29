package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.entity.*;
import com.tuaev.coffee_machine.exception.NotEnoughResourcesException;
import com.tuaev.coffee_machine.exception.NotFoundCoffeeMachineException;
import com.tuaev.coffee_machine.exception.NotRecipeByNameException;
import com.tuaev.coffee_machine.repositories.OrderRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class DefaultOrderService implements OrderService {

    private OrderRepo orderRepo;
    private ResourceService resourceService;
    private RecipeService recipeService;
    private CoffeeMachineService coffeeMachineService;
    private IngredientService ingredientService;

    @Transactional
    @Override
    public ResponseEntity<String> save(Long coffeeMachineId, OrderDTO orderDTO) {
        orderRepo.save(createOrder(coffeeMachineId, orderDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body("Заказ сохранён");
    }

    @Override
    public String findPopularRecipe() {
        return orderRepo.findPopularDrink();
    }

    private Order createOrder(Long coffeeMachineId, OrderDTO orderDTO) {
        Order order = new Order();
        CoffeeMachine coffeeMachine = coffeeMachineService.findById(coffeeMachineId).orElseThrow(NotFoundCoffeeMachineException::new);
        List<Ingredient> ingredientsByRecipeName = ingredientService.findAllIngredientsByRecipeName(orderDTO.getCoffeeName());
        resourceService.isMachineHasEnoughResources(coffeeMachine.getResources(), ingredientsByRecipeName);
        Recipe recipe = recipeService.findByName(orderDTO.getCoffeeName()).orElseThrow(NotRecipeByNameException::new);
        Set<Ingredient> ingredients = recipe.getIngredients();
        List<Resource> resources = coffeeMachine.getResources().stream()
                .filter(resource ->
                        ingredients.stream().anyMatch(ingredient ->
                                ingredient.getName().equals(resource.getType()))).toList();
        for (Resource r : resources) {
            for (Ingredient i : ingredients) {
                if (i.getName().equals(r.getType())) {
                    r.setAmount(r.getAmount() - i.getAmount());
                }
            }
        }
        coffeeMachine.setResources(new HashSet<>(resources));
        order.setRecipe(recipe);
        order.setLocalDateTime(LocalDateTime.now());
        order.setCoffeeMachine(coffeeMachine);
        return order;
    }
}
