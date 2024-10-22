package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.entity.Resource;

import java.util.List;

public interface ResourceService {

    List<Resource> findByCoffeeMachineId(Long coffeeMachineId);
}
