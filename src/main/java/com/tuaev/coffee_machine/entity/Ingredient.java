package com.tuaev.coffee_machine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipes;

    public Ingredient() {
    }

    public Ingredient(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
