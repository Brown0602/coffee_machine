package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.entity.*;
import com.tuaev.coffee_machine.repositories.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class DefaultOrderService implements OrderService{

    private OrderRepo orderRepo;
    private RecipeService recipeService;
    private IngredientService ingredientService;
    private DrinkIngredientsService drinkIngredientsService;

    @Override
    public void save(OrderDTO orderDTO){
        orderRepo.save(createOrder(orderDTO));
    }

    @Override
    public String findPopularDrink() {
        return orderRepo.findPopularDrink();
    }


    private Order createOrder(OrderDTO orderDTO){
        Optional<Recipe> recipe = recipeService.findByName(orderDTO.getCoffeeName());
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Order order = new Order();
        order.setRecipe(recipe.get());
        order.setLocalDateTime(LocalDateTime.now());
        order.setCoffeeMachine(coffeeMachine);

        return order;
    }



}
