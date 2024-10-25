package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Recipe;
import com.tuaev.coffee_machine.entity.Resource;
import com.tuaev.coffee_machine.repositories.CoffeeMachineRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public void updateResources(Long id, List<Resource> resources) {
        Optional<CoffeeMachine> coffeeMachine = coffeeMachineRepo.findById(id);
        if (coffeeMachine.isPresent()) {
            Set<Resource> coffeeMachineResources = coffeeMachine.get().getResources();
            for (Resource coffeeMachineResource : coffeeMachineResources) {
                for (Resource resource : resources) {
                    if (coffeeMachineResource.getType().equals(resource.getType())) {
                        resource.setAmount(coffeeMachineResource.getAmount() + resource.getAmount());
                        break;
                    }
                }
            }
            coffeeMachineRepo.save(coffeeMachine.get());
        }
    }

    private CoffeeMachine createCoffeeMachine(CoffeeMachineDTO coffeeMachineDTO){
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setModel(coffeeMachineDTO.getModel());
        coffeeMachine.setResources(new HashSet<>(coffeeMachineDTO.getResources()));
        coffeeMachine.setRecipes(new HashSet<>(coffeeMachineDTO.getRecipes()));
        return coffeeMachine;
    }

}
