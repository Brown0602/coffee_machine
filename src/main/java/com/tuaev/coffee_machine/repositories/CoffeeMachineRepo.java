package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.CoffeeMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMachineRepo extends JpaRepository<CoffeeMachine, Long> {
}
