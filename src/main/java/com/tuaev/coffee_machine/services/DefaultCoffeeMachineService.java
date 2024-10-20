package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.repositories.CoffeeMachineRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultCoffeeMachineService implements CoffeeMachineService{

    private CoffeeMachineRepo coffeeMachineRepo;
    private OrderService orderService;

    @Override
    public Optional<CoffeeMachine> getCoffeeMachine() {
        return coffeeMachineRepo.findById(2L);
    }

  @Override
  public String findPopularDrink() {
    return orderService.findPopularDrink();
  }
}
