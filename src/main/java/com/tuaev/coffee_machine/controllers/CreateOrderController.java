package com.tuaev.coffee_machine.controllers;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.repositories.CoffeeMachineRepo;
import com.tuaev.coffee_machine.repositories.DrinkRepo;
import com.tuaev.coffee_machine.repositories.OrderRepo;
import com.tuaev.coffee_machine.entity.CoffeeMachine;
import com.tuaev.coffee_machine.entity.Drink;
import com.tuaev.coffee_machine.entity.Order;
import com.tuaev.coffee_machine.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class CreateOrderController {

    private OrderService orderService;

    @PostMapping("/api/v1/create/order")
    public void createOrder(@RequestBody OrderDTO orderDTO){
        orderService.save(orderDTO);
    }
}
