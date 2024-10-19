package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Drink;
import com.tuaev.coffee_machine.repositories.DrinkRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultDrinkService implements DrinkService{

    private DrinkRepo drinkRepo;

    @Override
    public Optional<Drink> getByName(String name) {
        return drinkRepo.findByName(name);
    }
}
