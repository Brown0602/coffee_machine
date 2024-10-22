package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.entity.*;
import com.tuaev.coffee_machine.repositories.OrderRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DefaultOrderService implements OrderService{

    private OrderRepo orderRepo;
    private RecipeService recipeService;
    private CoffeeMachineService coffeeMachineService;
    private ResourceService resourceService;

    @Transactional
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
        Optional<CoffeeMachine> coffeeMachine = coffeeMachineService.findById(25L);
        Set<Ingredient> ingredients = recipe.get().getIngredients();
        List<Resource> resources = resourceService.findByCoffeeMachineId(coffeeMachine.get().getId()).stream()
                .filter(resource ->
                        ingredients.stream()
                                .anyMatch(ingredient ->
                                        ingredient.getName().equals(resource.getType())))
                .toList();
        for (Resource r : resources) {
            for (Ingredient i : ingredients) {
                if (i.getName().equals(r.getType())){
                    r.setAmount(r.getAmount() - i.getAmount());
                }
            }
        }
        coffeeMachine.get().setResources(new HashSet<>(resources));
        Order order = new Order();
        recipe.ifPresent(order::setRecipe);
        order.setLocalDateTime(LocalDateTime.now());
        coffeeMachine.ifPresent(order::setCoffeeMachine);

        return order;
    }



}
