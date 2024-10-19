package com.tuaev.coffee_machine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "drink_ingredients")
@Entity
@Getter
@Setter
public class DrinkIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private Drink drink;
    @OneToMany(mappedBy = "drinkIngredients")
    private List<Ingredient> ingredients;
    @Column(name = "amount")
    private int amount;
}
