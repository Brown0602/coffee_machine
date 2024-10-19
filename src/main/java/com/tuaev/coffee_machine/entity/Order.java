package com.tuaev.coffee_machine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Table(name = "orders")
@Getter
@Setter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "datetime")
    private LocalDateTime dateTime;
    @OneToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private Drink drink;
    @OneToOne
    @JoinColumn(name = "coffee_machine_id", referencedColumnName = "id")
    private CoffeeMachine coffeeMachine;

}
