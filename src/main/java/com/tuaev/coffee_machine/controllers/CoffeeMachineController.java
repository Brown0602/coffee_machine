package com.tuaev.coffee_machine.controllers;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Ingredient;
import com.tuaev.coffee_machine.entity.Resource;
import com.tuaev.coffee_machine.services.CoffeeMachineService;
import com.tuaev.coffee_machine.services.IngredientService;
import com.tuaev.coffee_machine.services.OrderService;
import com.tuaev.coffee_machine.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@AllArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CoffeeMachineController {

    private OrderService orderService;
    private RecipeService recipeService;
    private CoffeeMachineService coffeeMachineService;
    private IngredientService ingredientService;

    @PostMapping("/coffee_machine")
    public void addCoffeeMachine(@RequestBody CoffeeMachineDTO coffeeMachineDTO) {
        coffeeMachineService.save(coffeeMachineDTO);
    }

    @PostMapping("/coffee_machine/{id}")
    public void updateResources(@PathVariable("id") Long id, @RequestBody List<Resource> resources) {
        coffeeMachineService.updateResources(id, resources);
    }

    @PostMapping("/order/{coffeeMachineId}")
    public String createOrder(@PathVariable("coffeeMachineId") Long coffeeMachineId, @RequestBody OrderDTO orderDTO) {
        Optional<CoffeeMachine> coffeeMachine = coffeeMachineService.findById(coffeeMachineId);
        if (coffeeMachine.isPresent() && recipeService.isRecipeName(orderDTO.getCoffeeName())) {
            List<Ingredient> ingredients = ingredientService.findAllIngredientsByRecipeName(orderDTO.getCoffeeName());
            if (coffeeMachine.get().getResources().stream().allMatch(
                    resource ->
                            ingredients.stream().allMatch(ingredient ->
                                    ingredient.getAmount() <= resource.getAmount()))) {
                orderService.save(coffeeMachineId, orderDTO);
                return "Создан";
            }
            return "Нужно пополнить ресурсы кофемашины";
        }
        return null;
    }

    @PostMapping("/recipe/{coffeeMachineId}")
    public String createRecipe(@PathVariable("coffeeMachineId") Long coffeeMachineId, @RequestBody RecipeDTO recipeDto) {
        Optional<CoffeeMachine> coffeeMachine = coffeeMachineService.findById(coffeeMachineId);
        if (coffeeMachine.isPresent() && !recipeService.isRecipeName(recipeDto.getName())) {
            recipeService.save(coffeeMachineId, recipeDto);
            return "Сохранён";
        }
        return "Такой рецепт уже есть или нет такой кофемашины";
    }

    @GetMapping("/statistics/popular")
    public String getStatistics() {
        return recipeService.findPopularityRecipe();
    }
}
