package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.repositories.CoffeeMachineRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultCoffeeMachineService implements CoffeeMachineService{

    private CoffeeMachineRepo coffeeMachineRepo;

    @Override
    public Optional<CoffeeMachine> findById(Long id) {
        return coffeeMachineRepo.findById(id);
    }

    @Override
    public void save(CoffeeMachineDTO coffeeMachineDTO) {
        CoffeeMachine coffeeMachine = createCoffeeMachine(coffeeMachineDTO);
        coffeeMachineRepo.save(coffeeMachine);
    }

    private CoffeeMachine createCoffeeMachine(CoffeeMachineDTO coffeeMachineDTO){
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setModel(coffeeMachineDTO.getModel());
        coffeeMachine.setResources(new HashSet<>(coffeeMachineDTO.getResources()));

        return coffeeMachine;
    }

}
