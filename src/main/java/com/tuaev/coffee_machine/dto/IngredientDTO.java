package com.tuaev.coffee_machine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Ингредиент")
public class IngredientDTO {

    @Schema(description = "Название ингредиента")
    private String name;
    @Schema(description = "Количество ингредиента")
    private int amount;
}
