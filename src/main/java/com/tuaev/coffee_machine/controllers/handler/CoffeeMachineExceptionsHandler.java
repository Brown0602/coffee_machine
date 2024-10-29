package com.tuaev.coffee_machine.controllers.handler;

import com.tuaev.coffee_machine.exception.*;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CoffeeMachineExceptionsHandler {

    @ExceptionHandler(NotFoundCoffeeMachineException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage notFoundCoffeeMachineException(NotFoundCoffeeMachineException exception){
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(NotEnoughResourcesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage notEnoughResourcesException(NotEnoughResourcesException exception){
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(RecipeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage recipeAlreadyExistsException(RecipeAlreadyExistsException exception){
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(ResourcesNotEqualIngredientsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage resourcesNotEqualIngredientsException(ResourcesNotEqualIngredientsException exception){
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(NotRecipeByNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage notRecipeByNameException(NotRecipeByNameException exception){
        return new ErrorMessage(exception.getMessage());
    }
}