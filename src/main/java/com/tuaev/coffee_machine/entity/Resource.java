package com.tuaev.coffee_machine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Table(name = "resources")
@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "resource")
    private String type;
    @Column(name = "amount")
    private int amount;

    @JsonIgnore
    @ManyToMany(mappedBy = "resources")
    private Set<CoffeeMachine> coffeeMachine;

    public Resource() {
    }

    public Resource(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}
