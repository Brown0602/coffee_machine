package com.tuaev.coffee_machine.services;

import com.tuaev.coffee_machine.dto.OrderDTO;
import com.tuaev.coffee_machine.entity.*;
import com.tuaev.coffee_machine.repositories.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultOrderService implements OrderService{

    private OrderRepo orderRepo;
    private DrinkService drinkService;
    private IngredientService ingredientService;
    private DrinkIngredientsService drinkIngredientsService;

    @Override
    public void save(OrderDTO orderDTO){
        orderRepo.save(createOrder(orderDTO));
    }

    @Override
    public String findPopularDrink() {
        return orderRepo.findPopularDrink();
    }

    private List<Ingredient> getIngredients(){
        return null;
    }

    private Order createOrder(OrderDTO orderDTO){
        Optional<Recipe> drink = getDrink(orderDTO);
        List<RecipeIngredients> drinkIngredients = drinkIngredientsService.getAllByIdDrinkId(drink.get());
        Order order = new Order();



        return order;
    }


    private Optional<Recipe> getDrink(OrderDTO orderDTO){
        return drinkService.getByName(orderDTO.getCoffeeName());
    }

}
