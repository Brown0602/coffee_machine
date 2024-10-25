package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;

public interface OrderService {
    void save(Long coffeeMachineId, OrderDTO orderDTO);

    String findPopularRecipe();

}
