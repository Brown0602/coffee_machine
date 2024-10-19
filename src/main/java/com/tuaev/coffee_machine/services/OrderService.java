package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;

public interface OrderService {
    void save(OrderDTO orderDTO);
}
