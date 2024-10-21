package com.tuaev.coffee_machine.dto;

import com.tuaev.coffee_machine.entity.Ingredient;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeDTO {

    private String name;
    private List<Ingredient> ingredients;

}
