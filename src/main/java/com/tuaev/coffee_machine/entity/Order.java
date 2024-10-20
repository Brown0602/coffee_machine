package com.tuaev.coffee_machine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "order")
@Getter
@Setter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;
    @OneToOne
    @JoinColumn(name = "coffee_machine_id", referencedColumnName = "id")
    private CoffeeMachine coffeeMachine;
    @Column(name = "order_time")
    private LocalDateTime localDateTime;

}
