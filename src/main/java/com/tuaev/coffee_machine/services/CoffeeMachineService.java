package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.dto.ResourceDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface CoffeeMachineService {

    Optional<CoffeeMachine> findById(Long id);

    ResponseEntity<String> save(CoffeeMachineDTO coffeeMachineDTO);

    ResponseEntity<String> updateResources(Long id, List<ResourceDTO> resourceDTOS);

}
