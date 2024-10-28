package com.tuaev.coffee_machine.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "API кофемашины",
                description = "Реализация работы кофемашины", version = "1.0.0",
                contact = @Contact(
                        name = "Туаев Владислав"
                )
        )
)
public class SwaggerConfig {
}
