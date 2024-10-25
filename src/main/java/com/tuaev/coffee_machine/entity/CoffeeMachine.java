package com.tuaev.coffee_machine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Table(name = "coffee_machine")
@Entity
public class CoffeeMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "model")
    private String model;
    @OneToOne(mappedBy = "coffeeMachine")
    private Order order;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "coffee_machine_recipe",
            joinColumns = @JoinColumn(name = "coffee_machine_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "coffee_machine_resources",
            joinColumns = @JoinColumn(name = "machine_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private Set<Resource> resources;
}
