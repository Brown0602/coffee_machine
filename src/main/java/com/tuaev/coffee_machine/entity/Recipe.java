package com.tuaev.coffee_machine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Table(name = "recipe")
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CoffeeMachine> getCoffeeMachines() {
        return coffeeMachines;
    }

    public void setCoffeeMachines(Set<CoffeeMachine> coffeeMachines) {
        this.coffeeMachines = coffeeMachines;
    }

    @Column(name = "description")
    private String description;
    @ManyToMany(mappedBy = "recipes")
    private Set<CoffeeMachine> coffeeMachines;
}
