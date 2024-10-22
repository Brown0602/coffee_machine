package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import java.util.Optional;

public interface CoffeeMachineService {

    Optional<CoffeeMachine> findById(Long id);

    void save(CoffeeMachineDTO coffeeMachineDTO);

}
