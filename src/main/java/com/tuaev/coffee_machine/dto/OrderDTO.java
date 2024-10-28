package com.tuaev.coffee_machine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Создание заказа")
public class OrderDTO {

    @Schema(description = "Название напитка")
    private String coffeeName;
}

