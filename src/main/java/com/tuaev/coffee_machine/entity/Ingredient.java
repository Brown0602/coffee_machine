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
    @Column(name = "amount")
    private int amount;
    @ManyToMany(mappedBy = "ingredients")
    private Set<CoffeeMachine> coffeeMachines;
    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipes;
}
