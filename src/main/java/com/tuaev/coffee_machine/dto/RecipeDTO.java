package com.tuaev.coffee_machine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;

@Getter
@Schema(description = "Рецепт напитка")
public class RecipeDTO {

    @Schema(description = "Название рецепта напитка")
    private String name;
    @Schema(description = "Список ингредиентов для приготовления напитка по рецепту")
    private List<IngredientDTO> ingredients;
}
