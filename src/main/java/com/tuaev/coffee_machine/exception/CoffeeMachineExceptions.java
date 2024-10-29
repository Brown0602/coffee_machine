package com.tuaev.coffee_machine.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CoffeeMachineExceptions{

    @ExceptionHandler(NotFoundCoffeeMachineException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String notFoundException(NotFoundCoffeeMachineException notFoundCoffeeMachineException){
        return new ErrorMessage("Нет такой кофемашины").getMessage();
    }

    @ExceptionHandler(NotEnoughResourcesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String notEnoughResourcesError(NotEnoughResourcesException enoughResourcesException){
        return new ErrorMessage("Не хватает ресурсов кофемашины").getMessage();
    }

    @ExceptionHandler(RecipeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String recipeAlreadyExistsException(RecipeAlreadyExistsException recipeAlreadyExistsException){
        return new ErrorMessage("Такой рецепт уже есть").getMessage();
    }

    @ExceptionHandler(ResourcesNotEqualIngredientsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String resourcesNotEqualIngredientsException(ResourcesNotEqualIngredientsException resourcesNotEqualIngredientsException){
        return new ErrorMessage("Нельзя добавить рецепт! Нет таких типов ресурсов в кофемашине").getMessage();
    }

    @ExceptionHandler(NotRecipeByNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String notRecipeByNameException(NotRecipeByNameException notRecipeByNameException){
        return new ErrorMessage("Нет рецепта с таким названием").getMessage();
    }
}