package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Resource;
import com.tuaev.coffee_machine.repositories.ResourceRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultResourceService implements ResourceService{

    private ResourceRepo resourceRepo;

    @Override
    public List<Resource> findByCoffeeMachineId(Long coffeeMachineId) {
        return resourceRepo.findByCoffeeMachineId(coffeeMachineId);
    }

}
