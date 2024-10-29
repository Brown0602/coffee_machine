package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.entity.Order;

public interface OrderService {
    Order save(Long coffeeMachineId, OrderDTO orderDTO);
}
