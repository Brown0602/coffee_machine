package com.tuaev.coffee_machine.controllers;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.dto.RecipeDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
import com.tuaev.coffee_machine.exception.NotFoundCoffeeMachineException;
import com.tuaev.coffee_machine.services.CoffeeMachineService;
import com.tuaev.coffee_machine.services.OrderService;
import com.tuaev.coffee_machine.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@AllArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Tag(name = "Кофемашина контроллер", description = "Содержит методы для взаимодействия с кофемашиной")
public class CoffeeMachineController {

    private OrderService orderService;
    private RecipeService recipeService;
    private CoffeeMachineService coffeeMachineService;

    @Operation(summary = "Создаёт новую кофемашину", description = "Позволяет создать новую кофемашину")
    @PostMapping("/coffee_machine")
    public ResponseEntity<String> addCoffeeMachine(@RequestBody CoffeeMachineDTO coffeeMachineDTO) {
        return coffeeMachineService.save(coffeeMachineDTO);
    }

    @Operation(summary = "Обновляет ресурсы кофемашины")
    @PostMapping("/coffee_machine/{id}")
    public ResponseEntity<String> updateResources(@PathVariable("id") Long id, @RequestBody List<ResourceDTO> resourceDTOS) {
        return coffeeMachineService.updateResources(id, resourceDTOS);
    }

    @Operation(summary = "Создаёт заказ на определённую кофемашину")
    @PostMapping("/order/{coffeeMachineId}")
    public ResponseEntity<String> createOrder(@PathVariable("coffeeMachineId") Long coffeeMachineId, @RequestBody OrderDTO orderDTO) {
        return orderService.save(coffeeMachineId, orderDTO);
    }

    @Operation(summary = "Добавляет рецепт к определённой кофемашины")
    @PostMapping("/recipe/{coffeeMachineId}")
    public ResponseEntity<String> createRecipe(@PathVariable("coffeeMachineId") Long coffeeMachineId, @RequestBody RecipeDTO recipeDto) {
        if (!recipeService.isRecipeName(recipeDto.getName())) {
            recipeService
                    .save(coffeeMachineService.findById(coffeeMachineId)
                    .orElseThrow(NotFoundCoffeeMachineException::new),
                    recipeDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такой рецепт уже есть");
    }

    @Operation(summary = "Выводит самый часто заказываемый напиток")
    @GetMapping("/statistics/popular")
    public ResponseEntity<String> getStatistics() {
        return ResponseEntity.status(HttpStatus.OK).body(recipeService.findPopularityRecipe());
    }
}
