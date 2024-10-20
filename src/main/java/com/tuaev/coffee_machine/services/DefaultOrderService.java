package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.entity.*;
import com.tuaev.coffee_machine.repositories.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DefaultOrderService implements OrderService{

    private OrderRepo orderRepo;
    private DrinkService drinkService;
    private CoffeeMachineService coffeeMachineService;
    private IngredientService ingredientService;
    private DrinkIngredientsService drinkIngredientsService;

    @Override
    public void save(OrderDTO orderDTO){
        orderRepo.save(createOrder(orderDTO));
    }

    private List<Ingredient> getIngredients(){
        return null;
    }

    private Order createOrder(OrderDTO orderDTO){
        Optional<Drink> drink = getDrink(orderDTO);
        List<DrinkIngredients> drinkIngredients = drinkIngredientsService.getAllByIdDrinkId(drink.get());
        CoffeeMachine coffeeMachine = getCoffeeMachine().get();
//        coffeeMachine.setVolumeGrain(coffeeMachine.getVolumeGrain() - );
        Order order = new Order();
        order.setDrink(drink.get());
        order.setDateTime(LocalDateTime.now());
        order.setCoffeeMachine(getCoffeeMachine().get());


        return order;
    }

    private Optional<CoffeeMachine> getCoffeeMachine(){
        return coffeeMachineService.getCoffeeMachine();
    }

    private Optional<Drink> getDrink(OrderDTO orderDTO){
        return drinkService.getByName(orderDTO.getName());
    }

}
