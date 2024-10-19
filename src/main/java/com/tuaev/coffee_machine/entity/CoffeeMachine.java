package com.tuaev.coffee_machine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "coffee_machine")
@Entity
public class CoffeeMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "volume_water")
    private int volumeWater;
    @Column(name = "volume_milk")
    private int volumeMilk;
    @Column(name = "volume_grain")
    private int volumeGrain;
}
