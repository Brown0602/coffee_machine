package com.tuaev.coffee_machine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;

@Getter
@Schema(description = "Кофемашина")
public class CoffeeMachineDTO {

    @Schema(description = "Задаётся модель кофемашины")
    private String model;
    @Schema(description = "Список ресурсов, которые кофемашина поддерживает")
    private List<ResourceDTO> resources;
    @Schema(description = "Список рецептов напитков, которые кофемашина поддерживает")
    private List<RecipeDTO> recipes;
}
