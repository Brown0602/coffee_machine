package com.tuaev.coffee_machine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Table(name = "ingredients")
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "ingredients")
    private Set<CoffeeMachineResources> coffeeMachineResources;
    @ManyToMany(mappedBy = "ingredients")
    private Set<RecipeIngredients> recipeIngredients;
}
