package com.tuaev.coffee_machine.controllers;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.services.CoffeeMachineService;
import com.tuaev.coffee_machine.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
@AllArgsConstructor
public class CoffeeMachineController {

    private OrderService orderService;
    private RecipeService recipeService;
    private CoffeeMachineService coffeeMachineService;

    @PostMapping("/order")
    public void createOrder(@RequestBody OrderDTO orderDTO){
        orderService.save(orderDTO);
    }

    @PostMapping("/recipe")
    public void createRecipe(@RequestBody RecipeDto recipeDto){
        recipeService.addRecipe(recipeDto);
    }

    @GetMapping("/statistics/popular")
    public String getStatistics(){
        return  coffeeMachineService.findPopularDrink();
    }
}
