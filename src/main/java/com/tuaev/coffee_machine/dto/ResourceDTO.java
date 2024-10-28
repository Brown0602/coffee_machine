package com.tuaev.coffee_machine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Создание ресурса")
public class ResourceDTO {

    @Schema(description = "Тип ресурса")
    private String type;
    @Schema(description = "Количество ресурса")
    private int amount;
}
