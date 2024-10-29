package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<String> save(Long coffeeMachineId, OrderDTO orderDTO);
}
