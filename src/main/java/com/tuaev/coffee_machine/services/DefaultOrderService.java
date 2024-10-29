package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.entity.*;
import com.tuaev.coffee_machine.exception.NotFoundCoffeeMachineException;
import com.tuaev.coffee_machine.repositories.OrderRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class DefaultOrderService implements OrderService {

    private OrderRepo orderRepo;
    private ResourceService resourceService;
    private RecipeService recipeService;
    private IngredientService ingredientService;
    private CoffeeMachineService coffeeMachineService;

    @Transactional
    @Override
    public Order save(Long coffeeMachineId, OrderDTO orderDTO) {
        return orderRepo.save(createOrder(coffeeMachineId, orderDTO));
    }

    private Order createOrder(Long coffeeMachineId, OrderDTO orderDTO) {
        CoffeeMachine coffeeMachine = coffeeMachineService.findById(coffeeMachineId).orElseThrow(() ->
                new NotFoundCoffeeMachineException("Нет такой кофемашины"));
        Set<Ingredient> recipeIngredients = ingredientService.getIngredientsByRecipeName(orderDTO, coffeeMachine);
        resourceService.checkSufficientResourcesForRecipe(coffeeMachine.getResources(), recipeIngredients);
        Recipe recipe = recipeService.getRecipeByName(orderDTO, coffeeMachine);
        Set<Resource> resources = resourceService.getResourcesByCoffeeMachine(coffeeMachine, recipe);
        coffeeMachine.setResources(resourceService.updateResourcesForRecipe(recipe, resources));
        Order order = new Order();
        order.setRecipe(recipe);
        order.setLocalDateTime(LocalDateTime.now());
        order.setCoffeeMachine(coffeeMachine);
        return order;
    }
}
