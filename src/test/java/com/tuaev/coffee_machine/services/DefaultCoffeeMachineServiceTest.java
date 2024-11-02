package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.CoffeeMachineDTO;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.repositories.CoffeeMachineRepo;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;


class DefaultCoffeeMachineServiceTest {

    @Test
    void testSave(){

        CoffeeMachineRepo coffeeMachineRepo = Mockito.mock(CoffeeMachineRepo.class);
        CoffeeMachineDTO coffeeMachineDTO = new CoffeeMachineDTO();
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        when(coffeeMachineRepo.save(coffeeMachine)).thenReturn(coffeeMachine);
        CoffeeMachineService coffeeMachineService = new DefaultCoffeeMachineService();
        CoffeeMachine result = coffeeMachineService.save(coffeeMachineDTO);
        Assertions.assertEquals(coffeeMachine, result);
    }
}
