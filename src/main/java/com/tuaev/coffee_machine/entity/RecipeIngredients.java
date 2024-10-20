package com.tuaev.coffee_machine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.service.spi.InjectService;

import java.util.List;
import java.util.Set;

@Table(name = "recipe_ingredients")
@Entity
@Getter
@Setter
public class RecipeIngredients {

    @EmbeddedId
    @Column(name = "recipe_id")
    private Long recipeId;
    @Column(name = "ingredients_id")
    private Long machineId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private Set<Ingredient> ingredients;
    @Column(name = "amount")
    private int amount;
}
