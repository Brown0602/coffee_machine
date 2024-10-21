package com.tuaev.coffee_machine.controllers;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.services.CoffeeMachineService;
import com.tuaev.coffee_machine.services.OrderService;
import com.tuaev.coffee_machine.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CoffeeMachineController {

    private OrderService orderService;
    private RecipeService recipeService;
    private CoffeeMachineService coffeeMachineService;

    @PostMapping("/order")
    public void createOrder(@RequestBody OrderDTO orderDTO){
        orderService.save(orderDTO);
    }

    @PostMapping("/recipe")
    public String createRecipe(@RequestBody RecipeDTO recipeDto){
        if (!recipeService.isRecipeName(recipeDto.getName())) {
            recipeService.addRecipe(recipeDto);
            return "Сохранён";
        }
        return "Такой рецепт уже есть";
    }

    @GetMapping("/statistics/popular")
    public String getStatistics(){
        return  recipeService.findPopularityRecipe();
    }

}
